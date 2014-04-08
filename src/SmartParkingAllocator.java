import com.google.common.base.Optional;

import java.util.List;

public class SmartParkingAllocator implements ParkinglotSelector{
    @Override
    public Optional<ParkingLot> find(List<ParkingLot> parkingLotList) {
        ParkingLot mostLot = parkingLotList.get(0);
        for (ParkingLot p : parkingLotList) {
            if (p.remaining() > mostLot.remaining()) {
                mostLot = p;
                return Optional.of(mostLot);
            }
        }
        return Optional.fromNullable(null);
    }
}
