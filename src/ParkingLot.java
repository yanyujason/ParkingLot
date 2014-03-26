import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final int capacity;
    private List<Car> carList;

    public ParkingLot(int capacity) {
        this.carList = new ArrayList<Car>();
        this.capacity = capacity;
    }

    public Optional<Ticket> park(Car car) {
        if (this.carList.size() < capacity && !containCar(car)) {
            carList.add(car);
            Optional<Ticket> ticket = Optional.of(new Ticket(car.getCarNumber()));
            return ticket;
        }
        return Optional.fromNullable(null);
    }

    public Optional<Car> pickUp(Optional<Ticket> ticket) {
        for (Car aCar : this.carList) {
            if (aCar.equals(new Car(ticket.get().getCarNumber()))) {
                this.carList.remove(aCar);
                return Optional.of(aCar);
            }
        }
        return Optional.fromNullable(null);
    }

    public List<Car> getCarList() {
        return carList;
    }

    private boolean containCar(Car car) {
        for (Car aCar : this.carList) {
            if (aCar.equals(car)) {
                return true;
            }
        }
        return false;
    }

}
