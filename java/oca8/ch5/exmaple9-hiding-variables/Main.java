public class Main {
    public static void main(String[] args) {
        RacingCar.main(args);
    }
}

class Car {
    public static int instanceCount = 0;
    protected String brand = "Volkswagen";
    public String toString() {
        return "Car " + brand + "-" + instanceCount;
    }
}

class RacingCar extends Car {
    private long instanceCount = 1;
    private static StringBuilder brand = new StringBuilder("Alfa Romeo");
    public String toString() {
        return "RacingCar " + brand + "-" + instanceCount + " (parent brand " + super.brand + " and parent count " + super.instanceCount + ")";
    }
    public static void main(String[] args) {
        System.out.println(new Car());
        System.out.println(new RacingCar());
        // *** non-static variable instanceCount cannot be referenced from a static context
        //System.out.println("instanceCount = " + instanceCount);
    }
}
