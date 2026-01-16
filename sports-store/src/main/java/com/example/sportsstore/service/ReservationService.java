package com.example.sportsstore.service;

import com.example.sportsstore.dto.request.ReserveRequest;
import com.example.sportsstore.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService 
{

  ReservationResponse reserve(ReserveRequest request);

  List<ReservationResponse> getAll();

  ReservationResponse getById(Long id);

  int expireReservations();
}
