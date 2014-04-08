import com.google.common.base.Optional;

import java.util.List;

public interface ParkinglotSelector {
    Optional<ParkingLot> find(List<ParkingLot> parkingLotList);
}
