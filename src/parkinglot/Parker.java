package parkinglot;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class Parker implements Parkable {
    protected List<ParkingLot> parkingLots;
    private ParkinglotSelector parkinglotSelector;
    private int counter;

    public Parker(ParkinglotSelector parkinglotSelector) {
        this.parkingLots = new ArrayList<ParkingLot>();
        this.parkinglotSelector = parkinglotSelector;
        counter = 0;
    }

    public Optional<Ticket> park(Car car) {
        Optional<ParkingLot> parkingLot = parkinglotSelector.find(parkingLots);
        if (parkingLot.isPresent()) {
            return parkingLot.get().park(car);
        }
        return Optional.absent();
    }

    public Optional<Car> pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.contains(new Car(ticket.getCarNumber()))) {
                counter--;
                return parkingLot.pickUp(ticket);
            }
        }
        return Optional.absent();
    }

    @Override
    public void apply(Reporter reporter) {
        reporter.visitParker(parkingLots);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

}
