public class Main {
    public static void main(String[] args) {
        Car c = new Car();

        // why would you do this?
        // java.lang.ClassCastException: Car cannot be cast to RacingCar
        RacingCar rc = (RacingCar)c;
    }
}

class Car { }
class RacingCar extends Car { }
