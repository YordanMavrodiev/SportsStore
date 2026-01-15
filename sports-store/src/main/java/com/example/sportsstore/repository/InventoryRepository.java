package com.example.sportsstore.repository;

import com.example.sportsstore.domain.entity.Inventory;
import com.example.sportsstore.domain.enums.Size;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> 
{

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select i from Inventory i where i.product.id = :productId and i.size = :size")
  Optional<Inventory> findForUpdate(@Param("productId") Long productId, @Param("size") Size size);
}
