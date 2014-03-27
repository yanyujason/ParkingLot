import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class ParkingLotTest {

    private ParkingLot bigParkingLot;
    private ParkingLot smallParkingLot;
    private Car car_1;
    private Car car_2;
    private ParkingAllocator parkingAllocator;

    @Before
    public void setup() {
        smallParkingLot = new ParkingLot(1, 1);
        bigParkingLot = new ParkingLot(2, 2); // if the lot number is the same? stable sort? tests can pass
        car_1 = new Car("N123456");
        car_2 = new Car("N654321");
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(smallParkingLot);
        parkingLotList.add(bigParkingLot);
        parkingAllocator = new ParkingAllocator(parkingLotList);
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

    @Test
    public void shouldParkToParkingLotOne() {
        parkingAllocator.park(car_1);

        assertTrue(smallParkingLot.getCarList().contains(car_1));
        assertFalse(bigParkingLot.getCarList().contains(car_1));
    }

    @Test
    public void shouldParkToParkingLotTwoWhenFirstIsFull(){
        parkingAllocator.park(car_1);
        parkingAllocator.park(car_2);

        assertTrue(bigParkingLot.getCarList().contains(car_2));
    }

    @Test
    public void shouldNotParkToAnyParkingLotWhenAllFull(){
        parkingAllocator.park(car_1);
        parkingAllocator.park(car_2);
        Car car_3 = new Car("N333333");
        Car car_4 = new Car("N444444");
        parkingAllocator.park(car_3);
        Optional<Ticket> ticket_4 = parkingAllocator.park(car_4);

        assertEquals(smallParkingLot.getCarList().contains(car_2), false);
        assertFalse(ticket_4.isPresent());
    }

    @Test
    public void shouldNotParkAgainWhenParkAndPark(){
        Optional<Ticket> ticket = parkingAllocator.park(car_1);
        parkingAllocator.park(car_1);

        assertEquals(ticket, Optional.of(new Ticket("N123456")));
        assertTrue(smallParkingLot.getCarList().contains(car_1));
        assertFalse(bigParkingLot.getCarList().contains(car_1));
    }

}
