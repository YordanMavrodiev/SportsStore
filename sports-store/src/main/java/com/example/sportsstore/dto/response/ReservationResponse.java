package com.example.sportsstore.dto.response;

import com.example.sportsstore.domain.enums.ReservationStatus;
import com.example.sportsstore.domain.enums.Size;

import java.time.LocalDateTime;

public record ReservationResponse(
    Long id,
    Long userId,
    Long productId,
    Size size,
    int qty,
    ReservationStatus status,
    LocalDateTime expiresAt
) {}
