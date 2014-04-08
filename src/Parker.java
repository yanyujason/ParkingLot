import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class Parker {
    protected List<ParkingLot> parkingLots;
    private ParkinglotSelector parkinglotSelector;

    public Parker(ParkinglotSelector parkinglotSelector) {
        this.parkingLots = new ArrayList<ParkingLot>();
        this.parkinglotSelector = parkinglotSelector;
    }

    public Optional<Ticket> park(Car car) {
        Optional<ParkingLot> parkingLot = parkinglotSelector.find(parkingLots);
        if(parkingLot.isPresent())
        {
            return parkingLot.get().park(car);
        }
        return Optional.fromNullable(null);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

}
