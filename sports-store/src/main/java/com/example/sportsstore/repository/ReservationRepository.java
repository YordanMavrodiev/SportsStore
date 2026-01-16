package com.example.sportsstore.repository;

import com.example.sportsstore.domain.entity.InventoryReservation;
import com.example.sportsstore.domain.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<InventoryReservation, Long> 
{

  @Query("select r from InventoryReservation r where r.status = :status and r.expiresAt < :now")
  List<InventoryReservation> findExpired(@Param("status") ReservationStatus status, @Param("now") LocalDateTime now);
}
