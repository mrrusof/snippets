package backend;

import frontend.*;

public class Cpp {
    public void callPackageMethods() {
        String className = this.getClass().getSimpleName();

        // Loc in package backend
        new Java().m(className, "Java");
        new Groovy().m(className, "Groovy");
        ((Java) new Groovy()).m(className, "Java");

        // Loc in package frontend
        // *** Cannot find symbol m
        //new Javascript().m(className, "Javascript");
        ((Java) new Javascript()).m(className, "Java");
    }
}
