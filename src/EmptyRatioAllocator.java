import com.google.common.base.Optional;

import java.util.List;

public class EmptyRatioAllocator{
    private List<ParkingLot> parkingLotList;
    public EmptyRatioAllocator(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Optional<Ticket> park(Car car) {
        return find(parkingLotList).park(car);
    }

    private ParkingLot find(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotWithEmptyRatio = parkingLotList.get(0);
        for (ParkingLot p : parkingLotList) {
            if (p.emptyRate() > parkingLotWithEmptyRatio.emptyRate()) {
                parkingLotWithEmptyRatio = p;
            }
        }
        return parkingLotWithEmptyRatio;
    }
}
