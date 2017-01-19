package backend;

import frontend.*;

public class Ruby {
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
}
