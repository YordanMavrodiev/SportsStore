package com.example.sportsstore.mapper;

import com.example.sportsstore.domain.entity.InventoryReservation;
import com.example.sportsstore.domain.entity.Product;
import com.example.sportsstore.domain.entity.User;
import com.example.sportsstore.domain.enums.ReservationStatus;
import com.example.sportsstore.domain.enums.Size;
import com.example.sportsstore.dto.response.ReservationResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-16T20:09:10+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationResponse toResponse(InventoryReservation r) {
        if ( r == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        Long productId = null;
        Size size = null;
        int qty = 0;
        ReservationStatus status = null;
        LocalDateTime expiresAt = null;

        id = r.getId();
        userId = rUserId( r );
        productId = rProductId( r );
        size = r.getSize();
        qty = r.getQty();
        status = r.getStatus();
        expiresAt = r.getExpiresAt();

        ReservationResponse reservationResponse = new ReservationResponse( id, userId, productId, size, qty, status, expiresAt );

        return reservationResponse;
    }

    private Long rUserId(InventoryReservation inventoryReservation) {
        if ( inventoryReservation == null ) {
            return null;
        }
        User user = inventoryReservation.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long rProductId(InventoryReservation inventoryReservation) {
        if ( inventoryReservation == null ) {
            return null;
        }
        Product product = inventoryReservation.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
