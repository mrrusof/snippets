import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println(new RacingCar().getBrand());
        out.println(new RacingCarDefaultSuper().getBrand());

        // Class RacingCarWoConstructors does not define a constructor
        // that takes a string and no class inherits constructors.
        // The declaration of the class is commented.
        //out.println(new RacingCarWoConstructors("Ferrari").getBrand());

        Chimpanzee.main(args);
    }
}

// class Car {
//     private String brand;
//     public Car(String brand) {
//         super();
//         this.brand = brand;
//     }
//     public String getBrand() {
//         return brand;
//     }
// }

// class RacingCar extends Car {
//     public RacingCar(String brand) {
//         //out.println("This moves super to the second location, uh oh");
//         super(brand);
//     }

//     public RacingCar() {
//         this("Alfa Romeo");
//     }
// }
