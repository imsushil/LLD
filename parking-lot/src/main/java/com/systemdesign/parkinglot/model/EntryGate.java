package com.systemdesign.parkinglot.model;

import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.exceptions.ParkingLotException;
import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.services.TicketPriceCalculator;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author sushil
 */
public class EntryGate {
    private final ParkingLotManager parkingLotManager;
    private final TicketPriceCalculator ticketPriceCalculator;

    private EntryGate() {
        this.parkingLotManager = ParkingLotManager.getInstance();
        this.ticketPriceCalculator = TicketPriceCalculator.getInstance();
    }

    private static final class EntryGateHolder {
        private static final EntryGate INSTANCE = new EntryGate();
    }

    public static EntryGate getInstance() {
        return EntryGateHolder.INSTANCE;
    }

    private Optional<ParkingSpot> findFreeParkingSpot(VehicleType vehicleType) {
        return parkingLotManager.findFreeSpot(vehicleType);
    }

    public ParkingTicketDto issueTicket(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null) throw new IllegalArgumentException("vehicle cannot be null");

        ParkingSpot freeParkingSpot = findFreeParkingSpot(vehicle.getVehicleType())
                .orElseThrow(() -> new ParkingLotException("Parking is full"));
        Double ticketPrice = this.ticketPriceCalculator.getPrice(vehicle.getVehicleType(), LocalDateTime.now());
        return parkingLotManager.issueTicket(vehicle, freeParkingSpot, ticketPrice);
    }
}