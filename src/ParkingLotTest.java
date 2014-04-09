import com.google.common.base.Optional;
import org.junit.After;
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
    private Parker parkingAllocator;
    private Parker smartParkingAllocator;
    private Parker emptyRatioAllocator;
    private List<Parker> parkers;
    private Manager manager;

    @Before
    public void setup() {
        parkers = new ArrayList<Parker>();
        smallParkingLot = new ParkingLot(1);
        bigParkingLot = new ParkingLot(2);
        car_1 = new Car("N123456");
        car_2 = new Car("N654321");
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(smallParkingLot);
        parkingLotList.add(bigParkingLot);
        parkingAllocator = new Parker(new ParkingAllocator());
        smartParkingAllocator = new Parker(new SmartParkingAllocator());
        emptyRatioAllocator = new Parker(new EmptyRatioAllocator());
        parkingAllocator.addParkingLot(smallParkingLot);
        parkingAllocator.addParkingLot(bigParkingLot);
        smartParkingAllocator.addParkingLot(smallParkingLot);
        smartParkingAllocator.addParkingLot(bigParkingLot);
        emptyRatioAllocator.addParkingLot(smallParkingLot);
        emptyRatioAllocator.addParkingLot(bigParkingLot);

        parkers.add(parkingAllocator);
        parkers.add(smartParkingAllocator);
        parkers.add(emptyRatioAllocator);
        manager = new Manager(parkers);
    }

    @After
    public void clearUp() {
        smallParkingLot.getCars().clear();
        bigParkingLot.getCars().clear();
    }

    @Test
    public void shouldParkCarWhenNotFull() {
        assertEquals(bigParkingLot.park(car_1), Optional.of(new Ticket("N123456")));
        assertTrue(bigParkingLot.contains(car_1));
    }

    @Test
    public void shouldNotParkCarWhenFull() {
        smallParkingLot.park(car_1);
        Optional<Ticket> ticket_2 = smallParkingLot.park(car_2);

        assertFalse(smallParkingLot.contains(car_2));
        assertEquals(ticket_2.isPresent(), false);
    }

    @Test
    public void shouldCountOnceWhenParkAndParkAgain() {
        Optional<Ticket> ticket_1 = bigParkingLot.park(car_1);
        bigParkingLot.park(car_1);

        assertEquals(ticket_1, Optional.of(new Ticket("N123456")));
        assertTrue(bigParkingLot.contains(car_1));
        assertEquals(bigParkingLot.remaining(), 1);
    }

    @Test
    public void shouldPickUpCarWhenCarIsInParkingLot() {
        Optional<Ticket> ticket = bigParkingLot.park(car_1);
        Optional<Car> myCar = bigParkingLot.pickUp(ticket.get());

        assertEquals(myCar, Optional.of(car_1));
        assertEquals(bigParkingLot.remaining(), 2);
    }

    @Test
    public void shouldNotPickUpCarWhenNotInParkingLot() {
        Optional<Ticket> ticketOptional = Optional.of(new Ticket("N888888"));
        Optional<Car> carOptional = bigParkingLot.pickUp(ticketOptional.get());

        assertEquals(carOptional, Optional.fromNullable(null));
    }

    @Test
    public void shouldParkToParkingLotOne() {
        parkingAllocator.park(car_1);

        assertTrue(smallParkingLot.contains(car_1));
        assertFalse(bigParkingLot.contains(car_1));
    }

    @Test
    public void shouldParkToParkingLotTwoWhenFirstIsFull() {
        parkingAllocator.park(car_1);
        parkingAllocator.park(car_2);

        assertTrue(bigParkingLot.contains(car_2));
    }

    @Test
    public void shouldNotParkToAnyParkingLotWhenAllFull() {
        parkingAllocator.park(car_1);
        parkingAllocator.park(car_2);
        parkingAllocator.park(new Car("N333333"));

        assertFalse(smallParkingLot.contains(car_2));
        assertFalse(parkingAllocator.park(new Car("N444444")).isPresent());
    }

    @Test
    public void shouldParkToSecondParkingLot() {
        smartParkingAllocator.park(car_1);
        assertEquals(bigParkingLot.contains(car_1), true);
    }

    @Test
    public void shouldParkToFirstUsingEmptyRatioAllocator() {
        bigParkingLot.park(car_1);
        emptyRatioAllocator.park(car_2);
        assertTrue(smallParkingLot.contains(car_2));
    }

    @Test
    public void shouldParkByManager() {
        assertEquals(manager.park(car_1), Optional.of(new Ticket(("N123456"))));
    }

    @Test
    public void shouldPickUpByManager() {
        Optional<Ticket> ticket = manager.park(car_1);
        assertEquals(manager.pickUp(ticket.get()), Optional.of(car_1));
    }

    @Test
    public void shouldParkBySuperManager(){
        List<Manager> managers = new ArrayList<Manager>();
        managers.add(manager);
        SuperManager superManager = new SuperManager(managers);
        assertEquals(superManager.park(car_1), Optional.of(new Ticket(("N123456"))));
    }
}