package com.systemdesign.parkinglot.dto;

import lombok.Builder;

/**
 * @author sushil
 */
@Builder
public record ParkingSpotDto(int floorNo, int spotNo) {}