import static java.lang.System.out;

public class Main {
    public static void main(String[] aa) {
        out.println("this program does nothing but the classes illustrate the use of final");
    }
}

class Car {
    public static final float getMaxKmH() {
        return 100;
    }
    public final String getBrand() {
        return "Volkswagen";
    }
}

class RacingCar extends Car {
    public static float getMaxKmH() { // compile error, cannot hide getMaxKmH()
        return 350;
    }
    public String getBrand() { // compile error, cannot override getBrand()
        return "Alfa Romeo";
    }
}
