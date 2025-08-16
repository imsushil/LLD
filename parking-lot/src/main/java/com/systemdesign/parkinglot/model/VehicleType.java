package com.systemdesign.parkinglot.model;

/**
 * @author sushil
 */
public enum VehicleType {
    TWO_WHEELER("TWO_WHEELER"),
    LIGHT_WEIGHT("LIGHT_WEIGHT"),
    HEAVY_WEIGHT("HEAVY_WEIGHT");

    private String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}