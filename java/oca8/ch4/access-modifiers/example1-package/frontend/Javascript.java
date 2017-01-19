package frontend;

import backend.*;

public class Javascript extends Java {

    public void callPackageMethods() {
        String className = this.getClass().getSimpleName();

        // Loc in package backend
        // m(String,String) is not public in Java; cannot be accessed from outside package
        //new Java().m(className, "Java");
        // m(String,String) is not public in Java; cannot be accessed from outside package
        //new Groovy().m(className, "Groovy");
        // m(String,String) is not public in Java; cannot be accessed from outside package
        //((Java) new Groovy()).m(className, "Java");

        // Loc in package frontend
        // *** Cannot find symbol m
        //new Javascript().m(className, "Javascript");
        // *** m(String,String) is not public in Java; cannot be accessed from outside package
        //((Java) new Javascript()).m(className, "Java");
    }

}
