package com.example.sportsstore.service.impl;

import com.example.sportsstore.domain.entity.Inventory;
import com.example.sportsstore.domain.entity.InventoryReservation;
import com.example.sportsstore.domain.entity.Product;
import com.example.sportsstore.domain.entity.User;
import com.example.sportsstore.domain.enums.ReservationStatus;
import com.example.sportsstore.dto.request.ReserveRequest;
import com.example.sportsstore.dto.response.ReservationResponse;
import com.example.sportsstore.exception.ApiException;
import com.example.sportsstore.mapper.ReservationMapper;
import com.example.sportsstore.repository.InventoryRepository;
import com.example.sportsstore.repository.ProductRepository;
import com.example.sportsstore.repository.ReservationRepository;
import com.example.sportsstore.repository.UserRepository;
import com.example.sportsstore.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final InventoryRepository inventoryRepository;
  private final ReservationRepository reservationRepository;
  private final ReservationMapper reservationMapper;

  @Override
  @Transactional
  public ReservationResponse reserve(ReserveRequest request) 
  {

    User user = userRepository.findById(request.userId())
        .orElseThrow(() -> new ApiException("User not found"));

    Product product = productRepository.findById(request.productId())
        .orElseThrow(() -> new ApiException("Product not found"));

    Inventory inv = inventoryRepository.findForUpdate(
            request.productId(), request.size())
        .orElseThrow(() -> new ApiException("Inventory not found for size " + request.size()));

    if (inv.getAvailableQty() < request.qty()) {
      throw new ApiException("Not enough stock. Available: " + inv.getAvailableQty());
    }

    inv.setAvailableQty(inv.getAvailableQty() - request.qty());

    InventoryReservation reservation = InventoryReservation.builder()
        .user(user)
        .product(product)
        .size(request.size())
        .qty(request.qty())
        .status(ReservationStatus.ACTIVE)
        .expiresAt(LocalDateTime.now().plusMinutes(10))
        .build();

    reservationRepository.save(reservation);
    return reservationMapper.toResponse(reservation);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReservationResponse> getAll() 
  {
    return reservationRepository.findAll()
        .stream()
        .map(reservationMapper::toResponse)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public ReservationResponse getById(Long id) 
  {
    var r = reservationRepository.findById(id)
        .orElseThrow(() -> new ApiException("Reservation not found"));
    return reservationMapper.toResponse(r);
  }

  @Override
  @Transactional
  public int expireReservations() 
  {

    var expired = reservationRepository.findExpired(
        ReservationStatus.ACTIVE, LocalDateTime.now());

    for (var r : expired) {

      var inv = inventoryRepository.findForUpdate(
              r.getProduct().getId(), r.getSize())
          .orElseThrow(() -> new ApiException("Inventory not found"));

      inv.setAvailableQty(inv.getAvailableQty() + r.getQty());
      r.setStatus(ReservationStatus.EXPIRED);
    }

    return expired.size();
  }
}
