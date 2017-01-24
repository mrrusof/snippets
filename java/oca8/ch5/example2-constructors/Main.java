import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println(new RacingCar().getBrand());
        out.println(new RacingCarDefaultSuper().getBrand());
        //        out.println(new RacingCarWoConstructors("Ferrari").getBrand());
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
