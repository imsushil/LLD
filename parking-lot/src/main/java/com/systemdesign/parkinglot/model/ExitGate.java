package com.systemdesign.parkinglot.model;

import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.services.TicketPriceCalculator;
import com.systemdesign.parkinglot.services.TicketService;

/**
 * @author sushil
 */
public class ExitGate {
    private final TicketPriceCalculator ticketPriceCalculator;
    private final ParkingLotManager parkingLotManager;
    private final TicketService ticketService;

    private ExitGate() {
        this.parkingLotManager = ParkingLotManager.getInstance();
        this.ticketPriceCalculator = TicketPriceCalculator.getInstance();
        this.ticketService = TicketService.getInstance();
    }

    private static final class ExitGateHolder {
        private static final ExitGate INSTANCE = new ExitGate();
    }

    public static ExitGate getInstance() {
        return ExitGateHolder.INSTANCE;
    }

    public Double getActualPrice(ParkingTicketDto parkingTicket) {
        VehicleType vehicleType = parkingTicket.vehicle().vehicleType();
        return this.ticketPriceCalculator.getPrice(vehicleType, parkingTicket.parkedAt());
    }

    public boolean pay(ParkingTicketDto parkingTicket, Double price) {
        this.ticketService.pay(parkingTicket.id(), price);
        this.parkingLotManager.vacateParkingSpot(parkingTicket);
        return true;
    }
}