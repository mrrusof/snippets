public class Main {
    public static void main(String[] aa) {
        RacingCar.main(aa);
    }
}

class Car {
    public static float getMaxKmH() {
        return 100;
    }
    public String getBrand() {
        return "Volkswagen";
    }
    public String toString() {
        return  getBrand() + " w/ max speed of " + getMaxKmH() + " km/h";
    }
}

class RacingCar extends Car {
    public static float getMaxKmH() {
        return 350;
    }
    public String getBrand() {
        return "Alfa Romeo";
    }
    public static void main(String[] aa) {
        System.out.println(new Car());
        System.out.println(new RacingCar());
    }
}
