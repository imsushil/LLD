package com.systemdesign.parkinglot.config;

import com.systemdesign.parkinglot.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sushil
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PricingDetail {
    private VehicleType vehicleType;
    private Double price;
}