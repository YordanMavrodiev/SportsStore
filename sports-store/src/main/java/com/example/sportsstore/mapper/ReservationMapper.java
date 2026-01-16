package com.example.sportsstore.mapper;

import com.example.sportsstore.domain.entity.InventoryReservation;
import com.example.sportsstore.dto.response.ReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper 
{

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "product.id")
    ReservationResponse toResponse(InventoryReservation r);
}
