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
            return Optional.of(new Ticket(car.getCarNumber()));
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

    private boolean containCar(Car car) {
        for (Car aCar : this.carList) {
            if (aCar.equals(car)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotFull() {
        if (carList.size() < capacity) {
            return true;
        }
        return false;
    }

    public boolean contains(Car car) {
        return this.carList.contains(car);
    }

    public int remaining() {
        return this.capacity - this.carList.size();
    }

    public double emptyRate() {
        if (this.capacity == 0)
        {
            return 1.0;
        }
        return remaining() / this.capacity;
    }

    public List<Car> getCars(){
        return this.carList;
    }
}
