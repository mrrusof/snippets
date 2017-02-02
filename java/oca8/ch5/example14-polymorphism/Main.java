public class Main {
    public static void main(String[] args) {
        RacingCar.main(args);
    }
}

class Car {}
class RacingCar extends Car {
    public static void main(String[] args) {
        Car c = new RacingCar();
    }
}

interface Printable {}
class Document implements Printable {
    public static void main(String[] args) {
        Printable p = new Document();
    }
}
