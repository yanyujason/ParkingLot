import com.google.common.base.Optional;

import java.util.List;

public class SmartParkingAllocator {
    private List<ParkingLot> parkingLotList;

    public SmartParkingAllocator(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Optional<Ticket> park(Car car) {
        return find(parkingLotList).park(car);
    }

    private ParkingLot find(List<ParkingLot> parkingLotList) {
        ParkingLot mostLot = parkingLotList.get(0);
        for (ParkingLot p : parkingLotList) {
            if (p.remaining() > mostLot.remaining()) {
                mostLot = p;
            }
        }
        return mostLot;
    }
}
