package com.systemdesign.parkinglot.pricingstrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public class PricingStrategyFactory {
    private static Map<PricingStrategyType, PricingStrategy> map = new HashMap<>();

    static {
        map.put(PricingStrategyType.FLAT, new FlatPricingStrategy());
        map.put(PricingStrategyType.HOURLY, new HourlyPricingStrategy());
    }

    public static PricingStrategy get(PricingStrategyType pricingStrategyType) {
        return map.get(pricingStrategyType);
    }
}