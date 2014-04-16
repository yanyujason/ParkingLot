package parkinglot;

import com.google.common.base.Optional;

import java.util.List;

public class ParkingAllocator implements ParkinglotSelector {

    @Override
    public Optional<ParkingLot> find(List<ParkingLot> parkingLotList) {
        ParkingLot selectedParkingLot = parkingLotList.get(0);
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.isNotFull()) {
                selectedParkingLot = parkingLot;
                return Optional.of(selectedParkingLot);
            }
        }
        return Optional.fromNullable(null);
    }
}
