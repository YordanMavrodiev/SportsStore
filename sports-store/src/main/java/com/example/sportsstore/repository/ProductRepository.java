package com.example.sportsstore.repository;

import com.example.sportsstore.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " + "WHERE LOWER(p.name) LIKE :pattern " + "OR LOWER(p.brand) LIKE :pattern")
    List<Product> search(@Param("pattern") String pattern);
}
