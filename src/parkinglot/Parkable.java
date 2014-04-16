package parkinglot;

import com.google.common.base.Optional;

public interface Parkable {
    Optional<Ticket> park(Car car);

    Optional<Car> pickUp(Ticket ticket);

    void apply(Reporter reporter);
}
