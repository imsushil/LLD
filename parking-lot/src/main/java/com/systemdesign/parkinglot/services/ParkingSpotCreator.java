package com.systemdesign.parkinglot.services;

import com.systemdesign.parkinglot.config.ParkingTypeAvailable;
import com.systemdesign.parkinglot.model.parkingspot.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author sushil
 */
public class ParkingSpotCreator {
    public static Map<Integer, List<ParkingSpot>> createParkingSpots(int noOfFloors, List<ParkingTypeAvailable> parkingTypeAvailable) {
        return IntStream.range(0, noOfFloors)
                .mapToObj(floor -> Map.of(floor, createParkingSpotsOfAllAvailableTypes(floor, parkingTypeAvailable)))
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static List<ParkingSpot> createParkingSpotsOfAllAvailableTypes(int floor, List<ParkingTypeAvailable> parkingTypeAvailable) {
        return parkingTypeAvailable.stream()
                .map(parkingType -> createParkingSpotsForType(floor, parkingType))
                .flatMap(Collection::stream)
                .toList();
    }

    private static List<ParkingSpot> createParkingSpotsForType(int floorNo, ParkingTypeAvailable parkingTypeAvailable) {
        ParkingType parkingType = parkingTypeAvailable.getType();
        int noOfSpots = parkingTypeAvailable.getNoOfSpots();
        return IntStream.range(0, noOfSpots)
                .mapToObj(spotNo -> createParkingSpot(parkingType, floorNo, spotNo))
                .toList();
    }

    private static ParkingSpot createParkingSpot(ParkingType parkingType, int floorNo, int spotNo) {
        return switch (parkingType) {
            case TWO_WHEELER -> new TwoWheelerParkingSpot(floorNo, spotNo);
            case LIGHT_WEIGHT -> new LightWeightVehicleParkingSpot(floorNo, spotNo);
            case HEAVY_WEIGHT -> new HeavyWeightVehicleParkingSpot(floorNo, spotNo);
        };
    }
}
