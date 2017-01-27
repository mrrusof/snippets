import static java.lang.System.out;

public class Main {
    public static void main(String[] aa) {
        out.println(RacingCar.getClassName());
        RacingCar.main(aa);
    }
}

class Car {
    public static String getClassName() {
        return "Car";
    }
}

class RacingCar extends Car {
    public static String getClassName() {
        // *** non-static variable super cannot be referenced from a static context
        //return super.getClassName() + " RacingCar";
        return "RacingCar";
    }
    public String toString() {
        return getClassName() + " (parent " + super.getClassName() + ")";
    }
    public static void main(String[] aa) {
        System.out.println(new RacingCar());
    }
}
