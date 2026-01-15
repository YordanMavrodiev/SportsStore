package com.example.sportsstore.mapper;

import com.example.sportsstore.domain.entity.Inventory;
import com.example.sportsstore.dto.response.InventoryResponse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper 
{

  public InventoryResponse toResponse(Inventory i) 
  {
    return new InventoryResponse(
        i.getId(),
        i.getProduct().getId(),
        i.getSize(),
        i.getAvailableQty()
    );
  }
}
