package main;

public class Tester {
    static Model m;

    public static void main(String[] args) {
        System.out.println("testing...");
        Main.main(new String[0]);
        Main main = new Main();
        main.main(new String[0]);
        main = null;
        main.main(new String[0]);

        Model mo = null;
        mo.m();

        m.m();
    }
}

class Model {
    public static void m() { System.out.println("m!"); }
}
