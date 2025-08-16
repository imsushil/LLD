package com.systemdesign.parkinglot.config;

import com.systemdesign.parkinglot.pricingstrategy.PricingStrategyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sushil
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PricingStrategyDetail {
    private PricingStrategyType name;
    private Double price;
    private List<PricingDetail> pricing;
}