package com.systemdesign.parkinglot.pricingstrategy;

import com.systemdesign.parkinglot.model.VehicleType;

import java.time.LocalDateTime;

/**
 * @author sushil
 */
public interface PricingStrategy {
    Double DEFAULT_PRICE = 0.0; // that strategy won't be in use
    Double getPrice(VehicleType vehicleType, LocalDateTime parkedAt);
}