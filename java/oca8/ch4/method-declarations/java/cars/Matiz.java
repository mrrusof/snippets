package cars;

import static java.lang.System.out;

import vehicles.Vehicle;

public class Matiz extends Vehicle {
    public void doStuff() {
        Vehicle v = new Vehicle();
        out.println("v.getName() = " + v.getName());
        v.setName("matiz"); // public, accessible
        out.println("v.getName() = " + v.getName());
        // v.appendToName(" for two"); // protected, not accesible from subclass
        // out.println("v.getName() = " + v.getName());
        // v.prependToName("red "); // package private access, not accessible from here
        // out.println("v.getName() = " + v.getName());
        // v.prepend("super "); // private, not accessible from here
        // out.println("v.getName() = " + v.getName());
    }
}
