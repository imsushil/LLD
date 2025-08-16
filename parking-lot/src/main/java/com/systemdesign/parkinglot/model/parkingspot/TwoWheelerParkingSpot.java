package com.systemdesign.parkinglot.model.parkingspot;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sushil
 */
@Getter
@Setter
public class TwoWheelerParkingSpot extends ParkingSpot {
    public TwoWheelerParkingSpot(int floorNo, int spotNo) {
        super(floorNo, spotNo);
    }

    @Override
    public ParkingType getParkingType() {
        return ParkingType.TWO_WHEELER;
    }
}