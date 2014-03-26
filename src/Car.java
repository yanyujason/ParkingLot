
public class Car {

    private String carNumber;

    public Car(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    @Override
    public boolean equals(Object o) {
        Car car = (Car) o;
        if (car.getCarNumber().equals(this.carNumber)) {
            return true;
        }
        return false;
    }
}
