package parkinglot;

import com.google.common.base.Optional;

import java.util.List;

public class EmptyRatioAllocator implements ParkinglotSelector{
    @Override
    public Optional<ParkingLot> find(List<ParkingLot> parkingLotList) {
        ParkingLot parkingLotWithEmptyRatio = parkingLotList.get(0);
        for (ParkingLot p : parkingLotList) {
            if (p.emptyRate() > parkingLotWithEmptyRatio.emptyRate()) {
                parkingLotWithEmptyRatio = p;
            }
            return Optional.of(parkingLotWithEmptyRatio);
        }
        return Optional.fromNullable(null);
    }
}
