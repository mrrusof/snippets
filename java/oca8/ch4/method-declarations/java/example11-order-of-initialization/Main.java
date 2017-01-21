public class Main {
    public static void main(String... args) {
        StaticFields.main(args);
        // the following line prints:
        // initializer: A = 6
        // constructor: A = 0 
        Instantiation i = new Instantiation();


        Car.main(args);

        Tricky.main(args);
    }
}

class StaticFields {
    static int L = 2;
    static int W = 3;
    static int H = 4;
    static int V;
    static { V = L * W * H; }
    public static void main(String[] args) {
        System.out.println("V = " + V);
    }
}

class Instantiation {
    int L = 2;
    int H = 3;
    int A = L * H;
    { System.out.println("initializer: A = " + A); }
    Instantiation() {
        A = 0;
        System.out.println("constructor: A = " + A);
    }
}

class Car {
    { System.out.println("initializing instance " + count); }
    float r, g, b;
    { r = g = b = 1f; }
    public String toString() {
        return "(" + r + "," + g + "," + b + ")";
    }
    Car(float r, float g, float b) {
        System.out.println("constructing car " + count);
        System.out.println(this);
        this.r = r;
        this.g = g;
        this.b = b;
        System.out.println(this);
        System.out.println("done constructing car " + count);
        count++;
    }

    static int count = 0;
    static { System.out.println(count + " cars created"); }

    public static void main(String... a) {
        System.out.println("Starting main program...");
        Car c1 = new Car(3, 2, 1);
        Car c2 = new Car(7, 6, 5);
    }
    { System.out.println("no more initializers for car " + count); }
    static { System.out.println("no more static initializers"); }
}

class Tricky {
    static { print(2); }
    static void print(int n) { System.out.println(n); }
    Tricky() { print(1); }
    { print(3); }
    public static void main(String... args) {
        System.out.println("program started");
    }
    static { new Tricky(); }
}
