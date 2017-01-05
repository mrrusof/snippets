package vehicles;

import static java.lang.System.out;

public class RoadVehicle {
    public void doStuff() {
        Vehicle v = new Vehicle();
        out.println("v.getName() = " + v.getName());
        v.setName("road vehicle"); // public, accessible
        out.println("v.getName() = " + v.getName());
        v.appendToName(" for one"); // protected, accessible
        out.println("v.getName() = " + v.getName());
        v.prependToName("red "); // package private access, accessible
        out.println("v.getName() = " + v.getName());
        // v.prepend("super "); // private access, not accesible
        // out.println("v.getName() = " + v.getName());
    }
}
