package com.systemdesign.parkinglot.model.parkingspot;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author sushil
 */
public enum ParkingType {
    TWO_WHEELER("TWO_WHEELER"),
    LIGHT_WEIGHT("LIGHT_WEIGHT"),
    HEAVY_WEIGHT("HEAVY_WEIGHT");

    private String name;

    ParkingType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static ParkingType fromString(String name){
        return ParkingType.valueOf(name);
    }
}