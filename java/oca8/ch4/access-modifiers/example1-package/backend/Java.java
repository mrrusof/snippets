package backend;

import main.*;
import frontend.*;

public class Java extends Cpp {
    String f = "Java";

    void m(String l, String r) {
        System.out.println("m: Loc " + l + ", Decl Java, Ref type " + r + ",\tObj type " + this.getClass().getSimpleName());
    }

    public void callPackageMethods() {
        String className = this.getClass().getSimpleName();

        // Loc in package backend
        new Java().m(className, "Java");
        new Groovy().m(className, "Groovy");
        ((Java) new Groovy()).m(className, "Java");

        // Loc in package frontend
        // *** Cannot find symbol m
        //new Javascript().m(className, "Javascript");
        ((Java) new Javascript()).m(className, "Java"); // OMFG!
        // *** m(String,String) is not public in TypeScript; cannot be accessed from outside package
        //new TypeScript().m(className, "TypeScript");
        // *** Cannot find symbol m
        //((Javascript) new TypeScript()).m(className, "Javascript");
        ((Java) new TypeScript()).m(className, "Java"); // OMFG! this is worse!
    }

    public void accessPackageFields() {
        String className = this.getClass().getSimpleName();

        Main.printFieldAccess(className, new Java().f, "Java", "Java");
        Main.printFieldAccess(className, new Groovy().f, "Groovy", "Groovy");
        Main.printFieldAccess(className, ((Java) new Groovy()).f, "Java", "Groovy");
        // *** f is not public in Java; cannot be accessed from outside package
        //Main.printFieldAccess(className, new Javascript().f, "Javascript", "Javascript");
        Main.printFieldAccess(className, ((Java) new Javascript()).f, "Java", "Javascript"); // again, OMFG!
        // *** f is not public in TypeScript; cannot be accessed from outside package
        //Main.printFieldAccess(className, new TypeScript().f, "TypeScript", "TypeScript");
        // *** f is not public in Java; cannot be accessed from outside package
        //Main.printFieldAccess(className, ((Javascript) new TypeScript()).f, "Javascript", "TypeScript");
        Main.printFieldAccess(className, ((Java) new TypeScript()).f, "Java", "TypeScript"); // again, OMFG! this is worse!
    }
}
