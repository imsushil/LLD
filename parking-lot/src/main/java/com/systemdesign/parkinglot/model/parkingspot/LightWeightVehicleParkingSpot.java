package com.systemdesign.parkinglot.model.parkingspot;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sushil
 */
@Getter
@Setter
public class LightWeightVehicleParkingSpot extends ParkingSpot {
    public LightWeightVehicleParkingSpot(int floorNo, int spotNo) {
        super(floorNo, spotNo);
    }

    @Override
    public ParkingType getParkingType() {
        return ParkingType.LIGHT_WEIGHT;
    }
}
