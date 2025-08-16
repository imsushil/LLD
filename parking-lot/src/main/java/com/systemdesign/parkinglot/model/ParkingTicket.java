package com.systemdesign.parkinglot.model;

import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author sushil
 */
@Builder
@Setter
@Getter
@ToString
public class ParkingTicket {
    private String id;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime parkedAt;
    private LocalDateTime vacatedAt;
    private Double price;
    private boolean paid;
}