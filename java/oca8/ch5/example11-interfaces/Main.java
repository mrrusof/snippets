//import pkg.AnotherPrinter;
import java.util.concurrent.TimeoutException;

import pkg1.*;

public class Main {
    public static void main(String[] args) {
        new NumberPrinter().print();
        new PrintablePrinter().print();
        new HelloPrinter().print();
        new PrintNumber().print();
        NumberPrinter7.main(args);
        System.out.println("################################################################################");
        System.out.println(Article2.d);
        System.out.println(Article2.pu);
        System.out.println(Article2.sd);
        System.out.println(Article2.spu);
        // error: cannot assign a value to final variable d
        //Article2.d = "bla";
        // error: cannot assign a value to final variable sd
        //Article2.sd = "bla";
        System.out.println("################################################################################");
        Article2.print();
        InheritArticle2.print();
        new HideArticle2().printFieldsThatHide();
        new HideArticle2().printHiddenFields();
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
    // static abstract void print(); // FAILS WITH "illegal combination of modifiers: abstract and static"

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

interface Document3 {
    void print();
}

interface Printer3 {
    void print();
}

class PrintNumber implements Document3, Printer3 {
    public void print() {
        System.out.println("number: " + 1);
    }
}

// interface Document4 {
//     // illegal combination of modifiers: abstract and static
//     static abstract void print();
// }

interface Document5 {
    String print();
}
interface Printer5 {
    void print();
}

// class PrintNumber5 implements Document5, Printer5 {
//     public String print() {
//         return "1";
//     }
//     public void print() {
//         System.out.println(1);
//     }
// }

// ERROR "types Printer5 and Document5 are incompatible; both define print(), but with unrelated return types"
//abstract class PrintStuff implements Document5, Printer5 { }

// ERROR "types Printer5 and Document5 are incompatible; both define print(), but with unrelated return types"
//interface IPrintStuff extends Document5, Printer5 { }

// interface Document6 {
//     private int DEFAULT_PAGE_COUNT = 1; // modifier private not allowed here
//     protected String DEFAULT_PAGE_SIZE = "A4"; // modifier protected not allowed here
//     abstract String DEFAULT_BODY = "hello"; // modifier abstract not allowed here
// }

interface Printer7 {
    String document() throws TimeoutException;
    default void print() {
        try {
            System.out.println("printed document: " + document());
        } catch(TimeoutException e) {
            System.out.println("the document took too long to render");
        }
    }
}

class NumberPrinter7 implements Printer7 {
    public String document() {
        return "1";
    }
    public static void main(String[] args) {
        new NumberPrinter7().print();
    }
}

interface Printer8 {
    // // *** illegal combination of modifiers: static and default
    // static default void print() {
    //     System.out.println("hello");
    // }

    // // *** modifier final not allowed here
    // final default void print() {
    //     System.out.println("hello");
    // }

    // // *** illegal combination of modifiers: abstract and default
    // abstract default void print() {
    //     System.out.println("hello");
    // }
}

interface Printer9 {
    // // *** modifier private not allowed here
    // private default void print() {
    //     System.out.println("hello");
    // }

    // // *** modifier protected not allowed here
    // protected default void print() {
    //     System.out.println("hello");
    // }
}

// Interfaces may have an empty body.
interface Article1 { }

// Interfaces may include fields. / Declaration, inheritance, and hiding of fields
// see pkg1.Article2

class InheritArticle2 implements Article2 {
    static void print() {
        System.out.println("InheritArticle2: " + sfpu); // PRINTS INHERITED FIELD
    }
}

class HideArticle2 implements Article2 {
    static StringBuilder sfpu = new StringBuilder("HideArticle2: static final public field"); // HIDES sfpu
    public void printFieldsThatHide() {
        System.out.println("HideArticle2: " + sfpu); // PRINTS FIELD THAT HIDES sfpu
    }
    public void printHiddenFields() {
        // System.out.println("HideArticle2: " + super.sfpu); // error: cannot find symbol sfpu
    }
}
