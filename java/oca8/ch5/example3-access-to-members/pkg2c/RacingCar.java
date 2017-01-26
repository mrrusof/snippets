package pkg2c;

import pkg1.Car;

public class RacingCar extends Car {
    public RacingCar(String brand) {
        super(brand);
    }
    public int getId() {
        return super.getId() + 100;
    }
    public String getRacingNumber() {
        return getBrand() + " " + getId();
    }
}
