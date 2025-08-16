package com.systemdesign.parkinglot.services;

import com.systemdesign.parkinglot.config.ConfigLoader;
import com.systemdesign.parkinglot.config.PricingStrategyDetail;
import com.systemdesign.parkinglot.model.VehicleType;
import com.systemdesign.parkinglot.pricingstrategy.PricingStrategy;
import com.systemdesign.parkinglot.pricingstrategy.PricingStrategyFactory;

import java.time.LocalDateTime;

/**
 * @author sushil
 */
public class TicketPriceCalculator {
    private final PricingStrategy pricingStrategy;

    private TicketPriceCalculator() {
        PricingStrategyDetail pricingStrategyDetail = ConfigLoader.getConfig().getPricingStrategy();
        this.pricingStrategy = PricingStrategyFactory.get(pricingStrategyDetail.getName());
    }

    private static final class TicketPriceCalculatorHolder {
        private static final TicketPriceCalculator INSTANCE = new TicketPriceCalculator();
    }

    public static TicketPriceCalculator getInstance() {
        return TicketPriceCalculatorHolder.INSTANCE;
    }

    public Double getPrice(VehicleType vehicleType, LocalDateTime parkedAt) {
        return this.pricingStrategy.getPrice(vehicleType, parkedAt);
    }
}