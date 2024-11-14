package com.kh.cig;

public enum RideCategory {
    NORMAL(10, 1, 5),
    PREMIUM(15, 2, 20);

    private final double ratePerKm;
    private final double ratePerMinute;
    private final double minimumFare;

    RideCategory(double ratePerKm, double ratePerMinute, double minimumFare) {
        this.ratePerKm = ratePerKm;
        this.ratePerMinute = ratePerMinute;
        this.minimumFare = minimumFare;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    public double getRatePerMinute() {
        return ratePerMinute;
    }

    public double getMinimumFare() {
        return minimumFare;
    }
}

