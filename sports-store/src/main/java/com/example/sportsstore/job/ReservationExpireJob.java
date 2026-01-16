package com.example.sportsstore.job;

import com.example.sportsstore.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationExpireJob 
{

  private final ReservationService reservationService;

  @Scheduled(fixedDelay = 60000)
  public void run() 
  {
    reservationService.expireReservations();
  }
}
