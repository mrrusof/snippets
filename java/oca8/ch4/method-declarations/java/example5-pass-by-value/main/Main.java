package main;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String... args) {

        {
            System.out.println("hola");
            int n = 3;
            passByValue(n);
            System.out.println("n = " + n);

            List<String> l = Arrays.asList("one", "two");
            System.out.println("l = " + l);
            setThree(l);
            System.out.println("l = " + l);
        }

        {
            int n = 1;
            incr(n);
            System.out.println("n = " + n);
            n = incr(n);
            System.out.println("n = " + n);
        }
        
    }

    static void passByValue(int i) {
        while(i > 0) {
            System.out.println("i = " + i);
            i--;
        }
    }

    static public void setThree(List<String> m) {
        // m.add("three"); // unsupported operation
        m.set(0, "three");
    }

    static public int incr(int n) {
        return ++n;
    }
}
