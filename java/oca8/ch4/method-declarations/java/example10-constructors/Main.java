public class Main {

  Main(int n) {
    System.out.println("Main(int)");
  }
  // constructor Main(int) is already defined in class Main
  // Main(int m) {
  //   System.out.println("Main(int)");
  // }
  Main(float m) {
    System.out.println("Main(float)");
  }
  public static void main(String... args) {
    new Main(1); // prints "Main(int)"
    new Main(1f); // prints "Main(float)"
    Car.main(args);
    ConstructorChaining.main(args);
    QuadPrismHeight2.main(args);
    Rectangle.main(args);
    PrivateConstructor.main(args);
  }

    // public static void main(String... args) {
    //     System.out.println("hola mundo");
    //     new Overload(1); // prints "Overload(int)"
    //     new Overload(1f); // prints "Overload(float)"
    // }
}

class Overload {
    Overload(int n) {
        System.out.println("Overload(int)");
    }
    // constructor Overload(int) is already defined in class Overload
    // Overload(int m) {
    //     System.out.println("Overload(int)");
    // }
    Overload(float n) {
        System.out.println("Overload(float)");
    }
}

class Car {
    private int weight;
    private String color;
    private static final String DEF_COLOR = "red";
    Car(int weight) {
        // cannot reference color before supertype constructor has been called
        // this(weight, color);

        // call to this must be first statement in constructor
        // System.out.println("Default color");
        // this(weight, "red");

        //this(weight, "red");

        // better than last attempt
        this(weight, DEF_COLOR);
    }
    Car(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
    public String toString() {
        return "Car weights " + weight + " kgs and is " + color + ".";
    }
    public static void main(String... args) {
        System.out.println(new Car(1000).toString());
    }
}

class ConstructorChaining {
    private String name;
    private Integer id;
    private Float red;
    ConstructorChaining() {
        this("default");
    }
    ConstructorChaining(String name) {
        this(name, 1);
    }
    ConstructorChaining(String name, int id) {
        this(name, id, 1.25f);
    }
    ConstructorChaining(String name, int id, float red) {
        this.name = name;
        this.id = id;
        this.red = red;
    }
    public String toString() {
        return "ConstructorChaining(" + name + ", " + id + ", " + red + ");";
    }
    public static void main(String... args) {
        System.out.println(new ConstructorChaining());
    }
}

class QuadPrismHeight2 { // initialization of final instance fields
    private final int h;
    private final int volume;
    QuadPrismHeight2(int w, int l) {
        volume = w * h * l;
    }
    {
        h = 2;
    }
    public String toString() {
        return "QuadPrismHeight2.volume = " + volume;
    }
    public static void main(String... args) {
        System.out.println(new QuadPrismHeight2(1, 3));
    }
}

class Rectangle {
    private String name;
    private final int l;
    private final int h;
    // private final int area;
    Rectangle(int l, int h) {
        this.l = l;
        this.h = h;
    } // compile error here: variable area might not have been initialized
    public String toString() {
        return "Rectangle '" + name + "', dimensions " + l + "x" + h;
    }
    public static void main(String... args) {
        // comment declaration of final instance field area
        // to print "Rectangle 'null', dimensions 2x3"
        System.out.println(new Rectangle(2,3));
    }
}

class PrivateConstructor {
    private static int count = 0;
    private PrivateConstructor() {
        count++;
    }
    public static void main(String... args) {
        new PrivateConstructor();
        System.out.println("PrivateConstructor.count = " + PrivateConstructor.count);
    }
}
