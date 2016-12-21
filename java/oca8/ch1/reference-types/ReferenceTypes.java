import static java.lang.System.out;
import java.util.Random;

public class ReferenceTypes {
    public static void main(String[] args) {
        // int value = null; // does not compile
        String s = null;
        java.util.Date today;
        String greeting;
        today = new java.util.Date();
        greeting = "hola";
        out.println(greeting);
        out.println(greeting.length());
        int len = greeting.length();
        // int bad = len.length(); // does not compile
        // int num, String value; // does not compile
        boolean b1, b2;
        String s1 = "1", s2;
        // double d1, double d2; // does not compile
        int i1; int i2;
        // int i3; i4; // does not compile
        // out.println(i1); // does not compile because i1 is uninitialized
        if((new Random()).nextInt(1) == 1) {
            i1 = 1;
            i2 = 1;
        } else
            i1 = 0;
        out.println(i1);
        // out.println(i2); // does not compile
    }
}
