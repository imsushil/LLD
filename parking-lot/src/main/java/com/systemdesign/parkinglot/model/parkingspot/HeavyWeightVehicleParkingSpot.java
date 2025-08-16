package com.systemdesign.parkinglot.model.parkingspot;

import lombok.Getter;

/**
 * @author sushil
 */
@Getter
public class HeavyWeightVehicleParkingSpot extends ParkingSpot {

    public HeavyWeightVehicleParkingSpot(int floorNo, int spotNo) {
        super(floorNo, spotNo);
    }

    @Override
    public ParkingType getParkingType() {
        return ParkingType.HEAVY_WEIGHT;
    }
}