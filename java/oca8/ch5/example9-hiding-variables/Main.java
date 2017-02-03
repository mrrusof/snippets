public class Main {
    public static void main(String[] args) {
        Formula1Car.main(args);
    }
}

class Car {
    public static int cylinders = 4;
    private String brand = "Volkswagen";
    public String toString() {
        return "Car " + brand + "-" + cylinders;
    }
}

class RacingCar extends Car {
    protected long cylinders = 6;
    protected static StringBuilder brand = new StringBuilder("Alfa Romeo");

    public String toString() {
        return "RacingCar " + brand + "-" + cylinders + " descendant of " + super.toString();
    }
}

class Formula1Car extends RacingCar {
    private short cylinders = 4;
    public String toString() {
        return "Formula1Car " + brand + "-" + cylinders + " descendant of " + super.brand + "-" + super.cylinders;
    }
    public static void main(String[] args) {
        // prints "Car Volkswagen-4"
        System.out.println(new Car());
        // prints "RacingCar Alfa Romeo-6 descendant of Car Volkswagen-4"
        System.out.println(new RacingCar());
        // prints "Formula1Car Alfa Romeo-4 descendant of Alfa Romeo-6"
        System.out.println(new Formula1Car());
        // *** non-static variable instanceCount cannot be referenced from a static context
        //System.out.println("instanceCount = " + instanceCount);
    }
}
