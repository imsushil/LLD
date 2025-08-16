package com.systemdesign.parkinglot;

import com.systemdesign.parkinglot.dto.ParkingTicketDto;
import com.systemdesign.parkinglot.exceptions.ParkingLotException;
import com.systemdesign.parkinglot.model.EntryGate;
import com.systemdesign.parkinglot.model.ExitGate;
import com.systemdesign.parkinglot.model.Vehicle;
import com.systemdesign.parkinglot.model.VehicleType;

import static com.systemdesign.parkinglot.utils.JsonUtils.toJson;

/**
 * @author sushil
 */
public class Application {
    public static void main(String[] args) {
        EntryGate entryGate = EntryGate.getInstance();
        ExitGate exitGate = ExitGate.getInstance();

        try {
            // car arrives
            System.out.println("Vehicle enters the parking lot");
            Vehicle car = Vehicle.builder()
                    .vehicleType(VehicleType.LIGHT_WEIGHT)
                    .numberPlate("25-BH-0001-AA")
                    .build();

            // issue ticket
            ParkingTicketDto parkingTicket = entryGate.issueTicket(car);
            System.out.printf("Ticket issued: %s%n", toJson(parkingTicket));

            // paying at exit
            Double priceToPay = exitGate.getActualPrice(parkingTicket);
            System.out.printf("Price to be paid for parking: %.2f%n", priceToPay);

            exitGate.pay(parkingTicket, priceToPay);
            System.out.println("Ticket is paid.");
            System.out.println("Vehicle exits the parking lot");
        } catch (ParkingLotException e) {
            System.err.println(e.getMessage());
        }
    }
}