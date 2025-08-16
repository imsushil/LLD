package com.systemdesign.parkinglot.config;

import com.systemdesign.parkinglot.model.parkingspot.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sushil
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingTypeAvailable {
    private ParkingType type;
    private int noOfSpots;
}