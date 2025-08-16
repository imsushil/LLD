package com.systemdesign.parkinglot.config;

import com.systemdesign.parkinglot.parkingstrategy.ParkingStrategyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author sushil
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Config {
    private int noOfFloors;
    private List<ParkingTypeAvailable> parkingTypeAvailable;
    private PricingStrategyDetail pricingStrategy;
    private ParkingStrategyType parkingStrategy;
}