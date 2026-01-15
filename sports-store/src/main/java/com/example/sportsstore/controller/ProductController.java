package com.example.sportsstore.controller;

import com.example.sportsstore.dto.response.ProductResponse;
import com.example.sportsstore.mapper.ProductMapper;
import com.example.sportsstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController 
{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponse> all() 
    {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @GetMapping("/search")
    public List<ProductResponse> search(@RequestParam String q) 
    {
        String pattern = "%" + q.trim().toLowerCase() + "%";
        return productRepository.search(pattern)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
