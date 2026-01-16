package com.example.sportsstore;

import com.example.sportsstore.domain.enums.Size;
import com.example.sportsstore.dto.request.ReserveRequest;
import com.example.sportsstore.repository.InventoryRepository;
import com.example.sportsstore.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationConcurrencyTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    void onlyOneReservationWinsWhenLastItem() throws Exception {

        var req1 = new ReserveRequest(1L, 1L, Size.L, 1);
        var req2 = new ReserveRequest(2L, 1L, Size.L, 1);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch start = new CountDownLatch(1);

        Callable<Boolean> task1 = () -> {
            start.await();
            reservationService.reserve(req1);
            return true;
        };

        Callable<Boolean> task2 = () -> {
            start.await();
            reservationService.reserve(req2);
            return true;
        };

        Future<Boolean> f1 = pool.submit(task1);
        Future<Boolean> f2 = pool.submit(task2);

        start.countDown();

        int success = 0;
        int fail = 0;

        for (Future<Boolean> f : List.of(f1, f2)) {
    try {
        f.get(5, TimeUnit.SECONDS);
        success++;
    } catch (ExecutionException ex) {
        fail++;
    }
}


        pool.shutdown();

        assertEquals(1, success);
        assertEquals(1, fail);

        var inv = inventoryRepository.findForUpdate(1L, Size.L).orElseThrow();
        assertEquals(0, inv.getAvailableQty());
    }
}
