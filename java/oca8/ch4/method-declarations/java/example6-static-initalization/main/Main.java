package main;

public class Main {
    //private static final int C;

    public static void main(String... args) {
        // cannot assign a value to final variable C
        //C = 1;
        new Main().m();
    }

    //private static final int D;

    public void m() {
        // cannot assign a value to final variable D
        //D = 1;
        System.out.println("F = " + F);
    }

    //private static final int E;

    Main() {
        // cannot assign a value to final variable E
        //E = 1;
    }

    private static final int F;
    static {
        // this works
        F = 1;
    }
}
