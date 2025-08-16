package com.systemdesign.parkinglot.parkingstrategy;

import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.model.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sushil
 */
public interface ParkingStrategy {
    Optional<ParkingSpot> findParkingSpot(VehicleType vehicleType, Map<Integer, List<ParkingSpot>> parkingSpots);
}