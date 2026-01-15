package com.example.sportsstore.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    String brand,
    BigDecimal price
) {}
