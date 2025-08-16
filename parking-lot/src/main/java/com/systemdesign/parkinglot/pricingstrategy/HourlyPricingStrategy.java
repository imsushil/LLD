package com.systemdesign.parkinglot.pricingstrategy;

import com.systemdesign.parkinglot.config.Config;
import com.systemdesign.parkinglot.config.ConfigLoader;
import com.systemdesign.parkinglot.config.PricingDetail;
import com.systemdesign.parkinglot.config.PricingStrategyDetail;
import com.systemdesign.parkinglot.model.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author sushil
 */
public class HourlyPricingStrategy implements PricingStrategy {
    private static final Double TWO_WHEELER_PARKING_PRICE;
    private static final Double LIGHT_WEIGHT_VEHICLE_PARKING_PRICE;
    private static final Double HEAVY_WEIGHT_VEHICLE_PARKING_PRICE;

    static {
        Config config = ConfigLoader.getConfig();
        PricingStrategyDetail pricingStrategy = config.getPricingStrategy();
        if (pricingStrategy.getName().equals(PricingStrategyType.HOURLY)) {
            TWO_WHEELER_PARKING_PRICE = getPrice(pricingStrategy.getPricing(), VehicleType.TWO_WHEELER);
            LIGHT_WEIGHT_VEHICLE_PARKING_PRICE = getPrice(pricingStrategy.getPricing(), VehicleType.LIGHT_WEIGHT);
            HEAVY_WEIGHT_VEHICLE_PARKING_PRICE = getPrice(pricingStrategy.getPricing(), VehicleType.HEAVY_WEIGHT);
        } else {
            TWO_WHEELER_PARKING_PRICE = DEFAULT_PRICE;
            LIGHT_WEIGHT_VEHICLE_PARKING_PRICE = DEFAULT_PRICE;
            HEAVY_WEIGHT_VEHICLE_PARKING_PRICE = DEFAULT_PRICE;
        }
    }

    private static Double getPrice(List<PricingDetail> pricingDetailList, VehicleType vehicleType) {
        return pricingDetailList.stream()
                .filter(pricingDetail -> pricingDetail.getVehicleType().equals(vehicleType))
                .findFirst()
                .get()
                .getPrice();
    }

    @Override
    public Double getPrice(VehicleType vehicleType, LocalDateTime parkedAt) {
        long hours = Duration.between(parkedAt.truncatedTo(ChronoUnit.HOURS), LocalDateTime.now()).toHours();
        Double pricePerHour = getPricePerHour(vehicleType);
        if (hours == 0) {
            return pricePerHour;
        }
        return hours * pricePerHour;
    }

    private Double getPricePerHour(VehicleType vehicleType) {
        return switch (vehicleType) {
            case TWO_WHEELER -> TWO_WHEELER_PARKING_PRICE;
            case LIGHT_WEIGHT -> LIGHT_WEIGHT_VEHICLE_PARKING_PRICE;
            case HEAVY_WEIGHT -> HEAVY_WEIGHT_VEHICLE_PARKING_PRICE;
        };
    }
}
