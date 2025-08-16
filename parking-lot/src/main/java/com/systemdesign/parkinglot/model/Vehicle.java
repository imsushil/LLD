package com.systemdesign.parkinglot.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author sushil
 */
@Builder
@Getter
public class Vehicle {
    private VehicleType vehicleType;
    private String numberPlate;
}