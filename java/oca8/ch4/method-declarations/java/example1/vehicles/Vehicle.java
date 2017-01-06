package vehicles;

import static java.lang.System.out;

import cars.Matiz;

public class Vehicle {
    StringBuilder name = new StringBuilder();
    public String getName() {
        return name.toString();
    }
    public void setName(String name) {
        this.name = new StringBuilder(name);
    }
    protected void appendToName(String str) {
        name.append(str);
    }
    void prependToName(String str) {
        name = prepend(str);
    }
    private StringBuilder prepend(String str) {
        return new StringBuilder(str + name);
    }
    public void doStuff() {
        Matiz m = new Matiz();
        m.setName("matiz"); // public, inherited from Vehicle, accessible
        out.println("m.getName() = " + m.getName());
        m.appendToName(" from superclass"); // protected, inherited from Vehicle, accessible
        out.println("m.getName() = " + m.getName());
        // m.prependToName("red "); // package private access, not inherited, not accessible
        // out.println("m.getName() = " + m.getName());
        // m.prepend("super "); // private, not inherited, not accessible
        // out.println("m.getName() = " + m.getName());
    }
}
