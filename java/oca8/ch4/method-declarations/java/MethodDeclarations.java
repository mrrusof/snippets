import static java.lang.System.out;

import vehicles.Vehicle;
import vehicles.RoadVehicle;
import vehicles.TrueRoadVehicle;
import cars.Matiz;

public class MethodDeclarations {

    public static void main(String[] args) {

        /* Syntax of method declaration */
        /*
           <access modifier> <optional specifiers> <return type> <method name>(<parameter list>) <optional exception list>
         */

        {
            out.println("Access modifiers");

            out.println("Access members of Vehicle from class in another package");
            Vehicle v = new Vehicle();
            out.println("v.getName() = " + v.getName());
            v.setName("bicycle"); // public, accessible
            out.println("v.getName() = " + v.getName());
            //v.appendToName(" for one"); // protected, not accessible from here
            //out.println("v.getName() = " + v.getName());
            // v.prependToName("red "); // package private access, not accessible from here
            // out.println("v.getName() = " + v.getName());
            // v.prepend("red "); // private access, not accessible from here
            // out.println("v.getName() = " + v.getName());

            out.println("Access members from class in same package");
            RoadVehicle rv = new RoadVehicle();
            rv.doStuff();

            out.println("Access members of Vehicle from subclass in same package");
            TrueRoadVehicle trv = new TrueRoadVehicle();
            trv.doStuff();

            out.println("Access members of Vehicle from subclass in another package");
            Matiz m = new Matiz();
            m.doStuff();

            out.println("Access members of Matiz from superclass in another package");
            v.doStuff();
        }
    }
}
