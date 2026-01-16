package com.example.sportsstore.dto.request;

import com.example.sportsstore.domain.enums.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReserveRequest(
    @NotNull Long userId,
    @NotNull Long productId,
    @NotNull Size size,
    @Min(1) int qty
) {}
