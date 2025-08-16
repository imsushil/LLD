package com.systemdesign.parkinglot.services;

import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.mapper.ParkingTicketMapper;
import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.model.ParkingTicket;
import com.systemdesign.parkinglot.model.Vehicle;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sushil
 */
public class TicketService {
    private final Map<String, ParkingTicket> ticketIdToParkingTicket = new HashMap<>();
    private final ParkingTicketMapper parkingTicketMapper;

    private TicketService() {
        this.parkingTicketMapper = ParkingTicketMapper.getInstance();
    }

    private static final class TicketServiceHolder {
        private static final TicketService INSTANCE = new TicketService();
    }

    public static TicketService getInstance() {
        return TicketServiceHolder.INSTANCE;
    }

    public ParkingTicketDto issueTicket(ParkingSpot parkingSpot, Vehicle vehicle, Double price) {
        ParkingTicket parkingTicket = this.parkingTicketMapper.toEntity(parkingSpot, vehicle, price);
        ticketIdToParkingTicket.put(parkingTicket.getId(), parkingTicket);
        return this.parkingTicketMapper.toDto(parkingTicket);
    }

    public boolean pay(String ticketId, Double price) {
        ParkingTicket parkingTicket = ticketIdToParkingTicket.get(ticketId);
        parkingTicket.setPrice(price);
        parkingTicket.setPaid(Boolean.TRUE);
        parkingTicket.setVacatedAt(LocalDateTime.now());
        return true;
    }
}