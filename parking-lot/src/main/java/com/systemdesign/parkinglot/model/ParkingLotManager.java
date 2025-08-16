package com.systemdesign.parkinglot.model;

import com.systemdesign.parkinglot.config.Config;
import com.systemdesign.parkinglot.config.ConfigLoader;
import com.systemdesign.parkinglot.config.ParkingTypeAvailable;
import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.model.parkingspot.ParkingSpot;
import com.systemdesign.parkinglot.parkingstrategy.ParkingStrategy;
import com.systemdesign.parkinglot.parkingstrategy.ParkingStrategyFactory;
import com.systemdesign.parkinglot.services.ParkingSpotCreator;
import com.systemdesign.parkinglot.services.TicketService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author sushil
 */
public class ParkingLotManager {
    private final Map<Integer, List<ParkingSpot>> floorNoToParkingSpotsMap;
    private final ParkingStrategy parkingStrategy;
    private final TicketService ticketService;

    private ParkingLotManager() {
        ConfigLoader.init();
        Config config = ConfigLoader.getConfig();
        int noOfFloors = config.getNoOfFloors();
        List<ParkingTypeAvailable> parkingTypeAvailable = config.getParkingTypeAvailable();
        this.floorNoToParkingSpotsMap = ParkingSpotCreator.createParkingSpots(noOfFloors, parkingTypeAvailable);
        this.parkingStrategy = ParkingStrategyFactory.get(config.getParkingStrategy());
        this.ticketService = TicketService.getInstance();
    }

    private ParkingSpot getParkingSpot(int floorNo, int spotNo) {
        return this.floorNoToParkingSpotsMap.get(floorNo)
                .stream()
                .filter(parkingSpot -> parkingSpot.getSpotNo() == spotNo)
                .findFirst()
                .orElse(null);
    }

    private static final class ParkingLotManagerHolder {
        private static final ParkingLotManager INSTANCE = new ParkingLotManager();
    }

    public static ParkingLotManager getInstance() {
        return ParkingLotManagerHolder.INSTANCE;
    }

    public Optional<ParkingSpot> findFreeSpot(VehicleType vehicleType) {
        return this.parkingStrategy.findParkingSpot(vehicleType, this.floorNoToParkingSpotsMap);
    }

    public ParkingTicketDto issueTicket(Vehicle vehicle, ParkingSpot parkingSpot, Double price) {
        return this.ticketService.issueTicket(parkingSpot, vehicle, price);
    }

    public boolean vacateParkingSpot(ParkingTicketDto parkingTicket) {
        ParkingSpot parkingSpot = getParkingSpot(parkingTicket.parkingSpot().floorNo(), parkingTicket.parkingSpot().spotNo());
        parkingSpot.vacateParkingSpot();
        return true;
    }
}