import static java.lang.System.out;
import java.util.Arrays;

public class Main {
    public static void m(int n) {
        out.println("this is int n: " + n);
    }
    public static void m(byte n) {
        out.println("this is byte n: " + n);
    }
    // public static void m(byte m) { // method m(byte) is already defined in class Main
    //     out.println("this is byte m: " + m);
    // }
    // static void m(int n) { // method m(int) is already defined in class Main
    //     out.println("this is just static int n: " + n);
    // }
    public static void main(String... args) {
        m(1);
        byte b = 2;
        m(b);
        m(b + b);

        Varargs.m("one", "two");
    }
}

class Varargs {
    public static void m(String... a) {
        out.println(Arrays.toString(a));
    }
    public static void m(String[] a) { // cannot declare both m(String[]) and m(String...) in Varargs
        out.println(Arrays.toString(a));
    }
}
