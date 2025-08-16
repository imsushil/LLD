package com.systemdesign.parkinglot.parkingstrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public class ParkingStrategyFactory {
    private static Map<ParkingStrategyType, ParkingStrategy> map = new HashMap<>();

    static {
        map.put(ParkingStrategyType.DEFAULT, new DefaultParkingStrategy());
    }

    public static ParkingStrategy get(ParkingStrategyType parkingStrategyType) {
        return map.get(parkingStrategyType);
    }
}