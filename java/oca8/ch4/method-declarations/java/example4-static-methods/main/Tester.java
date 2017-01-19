package main;

import java.util.*;

import static java.util.Arrays.asList;
import static java.lang.System.out;

import static staticMembers.StaticMembers1.C;
import static staticMembers.StaticMembers2.C;

public class Tester {
    static Model m;

    static public int n = 10;

    public static void main(String args[]) {
        System.out.println("testing...");
        Main.main(new String[0]);
        Main main = new Main();
        main.main(new String[0]);
        main = null;
        main.main(new String[0]);

        Model mo = null;
        mo.m();

        m.m();

        System.out.println(Tester.n);
        Tester t1 = new Tester();
        Tester t2 = new Tester();
        t1.n = 20;
        t2.n = 30;
        System.out.println(Tester.n);

        Tester.n = 20;
        Tester t = new Tester();
        t.n = 30;
        t = null;
        t.n = 40;
        System.out.println(Tester.n);

        StaticVsInstance.main();
        Constants.m();

        new StaticImports().doEt();
    }
}

class Model {
    public static void m() { System.out.println("m!"); }
}


class StaticVsInstance {
    private String name = "are we human?";
    public static void first() { }
    public static void second() {   }
    public void third() { System.out.println(name); }
    //public static void fourth() { System.out.println(name); } // non static variable name cannot be referenced from a static context
    public static void main() {
        first();
        second();
        //third(); // inane
        //fourth();
        new StaticVsInstance().third();
        new StaticVsInstance().callThird();
    }
    public void callThird() {
        third();
    }
}

class Constants {
    public static final int THE_CONSTANT = 42;
    public static final List<String> values = new ArrayList<>();
    public static final int[] a = new int[3];

    private static final int NUM_SECONDS_PER_HOUR;
    static {
        int secsPerMin = 60;
        int minsPerHour = 60;
        NUM_SECONDS_PER_HOUR = secsPerMin * minsPerHour;
    }

    // *** variable CAN_I_NOT_BE_INITIALIZED not initialized in the default constructor
    //private static final int CAN_I_NOT_BE_INITIALIZED;

    // Constants() {
    //     CAN_I_NOT_BE_INITIALIZED = 1;
    // }

    public static void m() {
        System.out.println("THE_CONSTANT = " + THE_CONSTANT);
        // *** cannot assign a value to final variable THE_CONSTANT
        //THE_CONSTANT = 3; // nope
        values.add("are we human? or are we dancers?");
        System.out.println("values = " + values);
        System.out.println("a = " + Arrays.toString(a));

        System.out.println("NUM_SECONDS_PER_HOUR = " + NUM_SECONDS_PER_HOUR);
    }
}

class StaticImports {
    private void asList(Object... oo) {
        out.println("oo = " + Arrays.toString(oo));
    }
    public void doEt() {
        asList("one", "two");
        // *** incompatible types: void cannot be converted to List<String>
        // List<String> ss = asList("three", "four");
        List<String> ss = Arrays.asList("three", "four");
        out.println("ss = " + ss);

        // *** reference to C is ambiguous
        //out.println("C = " + C);
    }
}
