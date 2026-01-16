package com.example.sportsstore.controller;

import com.example.sportsstore.dto.request.ReserveRequest;
import com.example.sportsstore.dto.response.ReservationResponse;
import com.example.sportsstore.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController 
{

  private final ReservationService reservationService;

  @PostMapping
  public ReservationResponse reserve(@RequestBody ReserveRequest request) 
  {
    return reservationService.reserve(request);
  }

  @GetMapping
  public List<ReservationResponse> all() 
  {
    return reservationService.getAll();
  }

  @GetMapping("/{id}")
  public ReservationResponse byId(@PathVariable Long id) 
  {
    return reservationService.getById(id);
  }

  @PostMapping("/expire")
  public int expire() {
    return reservationService.expireReservations();
  }
}
