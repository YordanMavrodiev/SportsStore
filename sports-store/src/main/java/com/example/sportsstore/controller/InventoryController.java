package com.example.sportsstore.controller;

import com.example.sportsstore.dto.response.InventoryResponse;
import com.example.sportsstore.mapper.InventoryMapper;
import com.example.sportsstore.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController 
{

  private final InventoryRepository inventoryRepository;
  private final InventoryMapper inventoryMapper;

  @GetMapping
  public List<InventoryResponse> all() 
  {
    return inventoryRepository.findAll()
        .stream()
        .map(inventoryMapper::toResponse)
        .toList();
  }
}
