import com.google.common.base.Optional;

import java.util.List;

public class Manager implements Parkable{
    List<Parker> parkers;

    Manager(List<Parker> parkers){
        this.parkers = parkers;
    }
    public Optional<Ticket> park(Car car) {
        return parkers.get(0).park(car);
    }

    public Optional<Car> pickUp(Ticket ticket) {
        return parkers.get(0).pickUp(ticket);
    }
}
