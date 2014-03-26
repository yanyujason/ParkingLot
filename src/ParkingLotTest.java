import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ParkingLotTest {

    private ParkingLot bigParkingLot;
    private ParkingLot smallParkingLot;
    private Car car_1;
    private Car car_2;

    @Before
    public void setup() {
        bigParkingLot = new ParkingLot(10);
        smallParkingLot = new ParkingLot(1);
        car_1 = new Car("N123456");
        car_2 = new Car("N654321");
    }

    @Test
    public void shouldParkCarWhenNotFull() {
        assertEquals(bigParkingLot.park(car_1), Optional.of(new Ticket("N123456")));
        assertEquals(bigParkingLot.getCarList().size(), 1);
    }

    @Test
    public void shouldNotParkCarWhenFull() {
        smallParkingLot.park(car_1);
        Optional<Ticket> ticket_2 = smallParkingLot.park(car_2);
        assertEquals(smallParkingLot.getCarList().contains(car_2), false);
        assertEquals(ticket_2.isPresent(), false);
    }

    @Test
    public void shouldCountOnceWhenParkAndParkAgain() {
        Optional<Ticket> ticket_1 = bigParkingLot.park(car_1);
        bigParkingLot.park(car_1);
        assertEquals(ticket_1, Optional.of(new Ticket("N123456")));
        assertEquals(bigParkingLot.getCarList().contains(car_1), true);
        assertEquals(bigParkingLot.getCarList().size(), 1);
    }

    @Test
    public void shouldPickUpCarWhenCarIsInParkingLot() {
        Optional<Ticket> ticket = bigParkingLot.park(car_1);
        Optional<Car> myCar = bigParkingLot.pickUp(ticket);
        assertEquals(myCar, Optional.of(car_1));
        assertEquals(bigParkingLot.getCarList().size(), 0);
    }

    @Test
    public void shouldNotPickUpCarWhenNotInParkingLot() {
        Optional<Ticket> ticketOptional = Optional.of(new Ticket("N888888"));
        Optional<Car> carOptional = bigParkingLot.pickUp(ticketOptional);
        assertEquals(carOptional, Optional.fromNullable(null));
    }

}
