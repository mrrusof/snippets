/* Extending Car fails because Car does not have a default constructor
   that Java can call the constructor of RacingCarDefaultSuper. */
//public class RacingCarDefaultSuper extends Car {

/* Extending CarDefaultConstructor works because that class
   declares a constructor with no parameters. */
public class RacingCarDefaultSuper extends CarDefaultConstructor {
    public RacingCarDefaultSuper() {
        // A call to super() will happen before
        // executing the following line.
        System.out.println("RacingCarDefaultSuper() does nothing");
    }
}
