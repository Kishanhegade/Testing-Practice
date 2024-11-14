package com.kh.cig;

public class Ride {
    private final double distance; // in kilometers
    private final int time;        // in minutes
    private final RideCategory category;

    public Ride(double distance, int time, RideCategory category) {
        this.distance = distance;
        this.time = time;
        this.category = category;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public RideCategory getCategory() {
        return category;
    }
}

