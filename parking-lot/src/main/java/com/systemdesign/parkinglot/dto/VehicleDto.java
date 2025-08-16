package com.systemdesign.parkinglot.dto;

import com.systemdesign.parkinglot.model.VehicleType;
import lombok.Builder;

/**
 * @author sushil
 */
@Builder
public record VehicleDto(String vehicleNumberPlate, VehicleType vehicleType) {}