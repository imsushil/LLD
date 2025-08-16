package com.systemdesign.parkinglot.model.parkingspot;

import lombok.Getter;

/**
 * @author sushil
 */
@Getter
public abstract class ParkingSpot implements Cloneable {
    protected int floorNo;
    protected int spotNo;
    protected boolean occupied;

    protected ParkingSpot(int floorNo, int spotNo) {
        this.floorNo = floorNo;
        this.spotNo = spotNo;
    }

    public boolean isEmpty() {
        return !occupied;
    }

    public void occupy() {
        this.occupied = Boolean.TRUE;
    }

    public void vacateParkingSpot() {
        this.occupied = Boolean.FALSE;
    }

    public abstract ParkingType getParkingType();

    @Override
    public ParkingSpot clone() {
        try {
            return (ParkingSpot) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}