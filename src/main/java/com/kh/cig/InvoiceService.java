package com.kh.cig;

import java.util.List;

public class InvoiceService {
    public double calculateFare(Ride ride) {
        double fare = ride.getDistance() * ride.getCategory().getRatePerKm()
                + ride.getTime() * ride.getCategory().getRatePerMinute();
        return Math.max(fare, ride.getCategory().getMinimumFare());
    }

    public Invoice generateInvoice(List<Ride> rides) {
        double totalFare = rides.stream().mapToDouble(this::calculateFare).sum();
        return new Invoice(rides.size(), totalFare);
    }
}
