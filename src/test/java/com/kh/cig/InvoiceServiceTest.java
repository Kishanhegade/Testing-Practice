package com.kh.cig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceServiceTest {

    @Test
    public void testSingleRideFare() {
        InvoiceService service = new InvoiceService();
        Ride ride = new Ride(2.0, 5, RideCategory.NORMAL);
        double fare = service.calculateFare(ride);
        assertEquals(25.0, fare, 0.0);
    }
}
