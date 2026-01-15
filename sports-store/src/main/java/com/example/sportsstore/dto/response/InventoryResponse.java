package com.example.sportsstore.dto.response;

import com.example.sportsstore.domain.enums.Size;

public record InventoryResponse(
    Long id,
    Long productId,
    Size size,
    int availableQty
) {}
