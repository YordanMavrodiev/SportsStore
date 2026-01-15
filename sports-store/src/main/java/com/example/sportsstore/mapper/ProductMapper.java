package com.example.sportsstore.mapper;

import com.example.sportsstore.domain.entity.Product;
import com.example.sportsstore.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper 
{
  public ProductResponse toResponse(Product p) 
  {
    return new ProductResponse(p.getId(), p.getName(), p.getBrand(), p.getPrice());
  }
}
