package com.systemdesign.parkinglot.pricingstrategy;

import com.systemdesign.parkinglot.config.Config;
import com.systemdesign.parkinglot.config.ConfigLoader;
import com.systemdesign.parkinglot.model.VehicleType;

import java.time.LocalDateTime;

/**
 * @author sushil
 */
public class FlatPricingStrategy implements PricingStrategy {
    private static final Double PARKING_TICKET_PRICE;

    static {
        Config config = ConfigLoader.getConfig();
        if (config.getPricingStrategy().getName().equals(PricingStrategyType.FLAT)) {
            PARKING_TICKET_PRICE = config.getPricingStrategy().getPrice();
        } else {
            PARKING_TICKET_PRICE = DEFAULT_PRICE;
        }
    }

    @Override
    public Double getPrice(VehicleType vehicleType, LocalDateTime parkedAt) {
        return PARKING_TICKET_PRICE;
    }
}
