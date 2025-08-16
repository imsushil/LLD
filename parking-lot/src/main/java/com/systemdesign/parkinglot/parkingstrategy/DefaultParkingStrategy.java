package com.systemdesign.parkinglot.parkingstrategy;

import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.model.VehicleType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sushil
 */
public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findParkingSpot(VehicleType vehicleType, Map<Integer, List<ParkingSpot>> parkingSpots) {
        Optional<ParkingSpot> parkingSpotOptional = parkingSpots.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(parkingSpot -> parkingSpot.getParkingType().ordinal() >= vehicleType.ordinal())
                .filter(ParkingSpot::isEmpty)
                .findFirst();
        return parkingSpotOptional.map(ParkingSpot::clone);
    }
}