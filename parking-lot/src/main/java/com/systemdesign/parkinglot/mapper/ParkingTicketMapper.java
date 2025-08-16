package com.systemdesign.parkinglot.mapper;

import com.systemdesign.parkinglot.dto.ParkingSpotDto;
import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.dto.VehicleDto;
import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.model.ParkingTicket;
import com.systemdesign.parkinglot.model.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author sushil
 */
public class ParkingTicketMapper {
    private ParkingTicketMapper() {}

    private static final class ParkingTicketMapperHolder {
        private static final ParkingTicketMapper INSTANCE = new ParkingTicketMapper();
    }

    public static ParkingTicketMapper getInstance() {
        return ParkingTicketMapperHolder.INSTANCE;
    }

    public ParkingTicket toEntity(ParkingSpot parkingSpot, Vehicle vehicle, Double price) {
        String ticketId = UUID.randomUUID().toString();
        return ParkingTicket.builder()
                .id(ticketId)
                .parkingSpot(parkingSpot)
                .vehicle(vehicle)
                .parkedAt(LocalDateTime.now())
                .price(price)
                .paid(Boolean.FALSE)
                .build();
    }

    public ParkingTicketDto toDto(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot = parkingTicket.getParkingSpot();
        return ParkingTicketDto.builder()
                .id(parkingTicket.getId())
                .parkedAt(parkingTicket.getParkedAt())
                .vehicle(VehicleDto.builder()
                        .vehicleType(parkingTicket.getVehicle().getVehicleType())
                        .vehicleNumberPlate(parkingTicket.getVehicle().getNumberPlate())
                        .build()
                )
                .parkingSpot(ParkingSpotDto.builder()
                        .floorNo(parkingSpot.getFloorNo())
                        .spotNo(parkingSpot.getSpotNo())
                        .build()
                )
                .price(parkingTicket.getPrice())
                .build();
    }
}