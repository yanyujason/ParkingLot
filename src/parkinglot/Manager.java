package parkinglot;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Parkable {
    private List<Parkable> parkerList = new ArrayList<Parkable>();

    public void add(Parkable parker) {
        parkerList.add(parker);
    }

    public Optional<Ticket> park(Car car) {
        for (Parkable parker : parkerList) {
            Optional<Ticket> carTicket = parker.park(car);
            if (carTicket.isPresent()) {
                return carTicket;
            }
        }
        return Optional.absent();
    }

    public Optional<Car> pickUp(Ticket carTicket) {
        for (Parkable parker : parkerList) {
            Optional<Car> car = parker.pickUp(carTicket);
            if (car.isPresent()) {
                return car;
            }
        }
        return Optional.absent();
    }

    @Override
    public void apply(Reporter reporter) {
        reporter.visitManager(parkerList);
    }

    public List<Parkable> getParkers() {
        return this.parkerList;
    }

}
