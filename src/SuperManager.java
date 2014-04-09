import com.google.common.base.Optional;

import java.util.List;

public class SuperManager {
    List<Manager> managers;

    SuperManager(List<Manager> managers){
        this.managers = managers;
    }

    public Optional<Ticket> park(Car car) {
        return managers.get(0).park(car);
    }

    public Optional<Car> pickUp(Ticket ticket) {
        return managers.get(0).pickUp(ticket);
    }
}
