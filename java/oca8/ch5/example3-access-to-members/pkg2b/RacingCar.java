package pkg2b;

import pkg1.Car;

public class RacingCar extends Car {
    public RacingCar(String brand) {
        super(brand);
    }
    public String getRacingNumber() {
        return this.getBrand() + " " + super.getId();
    }
}
