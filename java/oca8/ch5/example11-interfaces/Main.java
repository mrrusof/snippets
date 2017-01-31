//import pkg.AnotherPrinter;

public class Main {
    public static void main(String[] args) {
        new NumberPrinter().print();
        new PrintablePrinter().print();
        new HelloPrinter().print();
    }
}

//interface Printer { // THIS AND THE FOLLOWING EVALUATE THE SAME
abstract interface Printer {
    // private String DEFAULT_PAPER_SIZE = "A4"; // FAILS WITH "error: modifier private not allowed here"

    // public String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    // static String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    // final String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    String DEFAULT_PAPER_SIZE = "A4";

    // private void print(); // FAILS WITH "error: modifier private not allowed here"
    // final void print(); // FAILS WITH "error: modifier final not allowed here"

    // public abstract void print(); // THE FOLLOWING EVALUATE THE SAME
    // public void print(); // THIS AND THE FOLLOWING EVALUATE THE SAME
    // abstract void print(); // THIS AND THE FOLLOWING EVALUATE THE SAME
    void print();
}

class NumberPrinter implements Printer {
    private String DEFAULT_PAPER_SIZE = "letter"; // THIS HIDES VARIABLE DEFAULT_PAPER_SIZE
    // void print() { // FAILS WITH "attempting to assign weaker access privileges; was public"
    public void print() {
        System.out.println("paper size: " + DEFAULT_PAPER_SIZE);
        for(int i = 0; i < 10; i++)
            System.out.println(i);
    }
}

// error: AnotherPrinter is not public in pkg; cannot be accessed from outside package

// class AnotherNumberPrinter implements AnotherPrinter {
//     public void print() {
//         System.out.println("hola");
//     }
// }


interface Renderable {
    String render();
}

interface Printable extends Renderable {
    void print();
}

class PrintablePrinter implements Printable {
  public String render() { return "document"; }
  public void print() { System.out.println(render()); }
}


interface Renderable2 {
  String render();
}

abstract class Printer2 implements Renderable2 {
  void print() { System.out.println(render()); }
}

class HelloPrinter extends Printer2 {
  public String render() { return "hello"; }
}
