package com.systemdesign.parkinglot.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author sushil
 */
@Builder
public record ParkingTicketDto(String id, ParkingSpotDto parkingSpot, VehicleDto vehicle, LocalDateTime parkedAt, Double price) {}