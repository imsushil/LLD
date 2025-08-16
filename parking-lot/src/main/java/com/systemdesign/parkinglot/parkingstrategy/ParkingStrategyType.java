package com.systemdesign.parkinglot.parkingstrategy;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public enum ParkingStrategyType {
    DEFAULT("Default"),
    NEAR_ENTRANCE("NearEntrance"),
    NEAR_ELEVATOR("NearElevator");

    private static final Map<String, ParkingStrategyType> parkingStrategyTypeMap = new HashMap<>();
    static {
        Arrays.stream(ParkingStrategyType.values()).forEach(parkingStrategyType -> {
            parkingStrategyTypeMap.put(parkingStrategyType.parkingStrategy.toLowerCase(), parkingStrategyType);
        });
    }

    private String parkingStrategy;

    ParkingStrategyType(String parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    @JsonCreator
    public static ParkingStrategyType fromString(String parkingStrategy) {
        ParkingStrategyType parkingStrategyType = parkingStrategyTypeMap.get(parkingStrategy.toLowerCase());
        if (parkingStrategyType == null) throw new IllegalArgumentException("pricingStrategy: " + parkingStrategyType + " is not supported");
        return parkingStrategyType;
    }
}