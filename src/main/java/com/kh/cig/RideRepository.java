package com.kh.cig;

import java.util.List;

public interface RideRepository {

    List<Ride> findByUserId(int userId);
}
