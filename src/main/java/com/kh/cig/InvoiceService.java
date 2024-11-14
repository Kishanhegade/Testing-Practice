package com.kh.cig;

import java.util.List;

public class InvoiceService {

    private final RideRepository rideRepository;

    public InvoiceService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double calculateFare(Ride ride) {
        double fare = ride.getDistance() * ride.getCategory().getRatePerKm()
                + ride.getTime() * ride.getCategory().getRatePerMinute();
        return Math.max(fare, ride.getCategory().getMinimumFare());
    }

    public Invoice generateInvoice(int userId) {
        List<Ride> rides = rideRepository.findByUserId(userId);
        double totalFare = rides.stream().mapToDouble(this::calculateFare).sum();
        return new Invoice(rides.size(), totalFare);
    }

}
