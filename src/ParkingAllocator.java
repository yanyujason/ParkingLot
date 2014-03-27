import com.google.common.base.Optional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParkingAllocator {

    private List<ParkingLot> parkingLotList;

    public ParkingAllocator(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotsSort(parkingLotList);
    }

    public Optional<Ticket> park(Car car) {
        for(ParkingLot parkingLot : parkingLotList){
            boolean isNotFull = parkingLot.isNotFull();
            if(isNotFull || (!isNotFull && parkingLot.getCarList().contains(car))){
                parkingLot.park(car);
                return Optional.of(new Ticket(car.getCarNumber()));
            }
        }
        return Optional.fromNullable(null);
    }

    private List<ParkingLot> parkingLotsSort (List<ParkingLot> parkingLotList) {
        Collections.sort(parkingLotList, new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot partingLot_1, ParkingLot partingLot_2) {
                return partingLot_1.getParkingLotNum() - partingLot_2.getParkingLotNum();
            }
        });
        return parkingLotList;
    }
}
