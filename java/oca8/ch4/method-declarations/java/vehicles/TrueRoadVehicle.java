package vehicles;

import static java.lang.System.out;

public class TrueRoadVehicle extends Vehicle {
    public void doStuff() {
        Vehicle v = new Vehicle();
        out.println("v.getName() = " + v.getName());
        v.setName("true road vehicle"); // public, accessible
        out.println("v.getName() = " + v.getName());
        v.appendToName(" for two"); // protected, accessible
        out.println("v.getName() = " + v.getName());
        v.prependToName("red "); // package private access, accessible
        out.println("v.getName() = " + v.getName());
        // v.prepend("super "); // private, not accessible
        // out.println("v.getName() = " + v.getName());
    }
}
