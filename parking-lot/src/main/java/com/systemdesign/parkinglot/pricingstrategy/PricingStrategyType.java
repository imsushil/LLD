package com.systemdesign.parkinglot.pricingstrategy;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public enum PricingStrategyType {
    FLAT("Flat"),
    HOURLY("Hourly");

    private static final Map<String, PricingStrategyType> pricingStrategyTypeMap = new HashMap<>();
    static {
        Arrays.stream(PricingStrategyType.values()).forEach(pricingStrategyTypeEnum -> {
            pricingStrategyTypeMap.put(pricingStrategyTypeEnum.pricingStrategy.toLowerCase(), pricingStrategyTypeEnum);
        });
    }

    private String pricingStrategy;

    PricingStrategyType(String pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @JsonCreator
    public static PricingStrategyType fromString(String pricingStrategy) {
        PricingStrategyType pricingStrategyType = pricingStrategyTypeMap.get(pricingStrategy.toLowerCase());
        if (pricingStrategyType == null) throw new IllegalArgumentException("pricingStrategy: " + pricingStrategy + " is not supported");
        return pricingStrategyType;
    }
}