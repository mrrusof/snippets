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
        System.out.println("################################################################################");
        new ImplementPrinter1().print();
        System.out.println("################################################################################");
        ConcreteOverridePrinter4.main(args);
        ConcreteHidePrinter4.main(args);
        System.out.println("################################################################################");
        new WhoWins().print();
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




// Interfaces may include abstract, default, and/or static methods.

interface Article3 {
    void a();
    default void d() {
        System.out.println("Article3: default method");
    }
    static void s() {
        System.out.println("Article3: static method");
    }
}



// Interfaces may not be `private`.

// error: modifier private not allowed here
//private interface Article4 { }

// Interfaces may not be `protected`.

// error: modifier protected not allowed here
//protected interface Article5 { }

// Interfaces may not be `final`.

// error: illegal combination of modifiers: interface and final
//final interface Article6 { }


interface Printer1 {
                      void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //       abstract void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //public abstract void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //public          void print();

    //private         void print(); // error: modifier private not allowed here
    //final           void print(); // error: modifier final not allowed here
    //static          void print(); // error: missing method body, or declare abstract
    //protected       void print(); // error: modifier protected not allowed here

    // Cannot declare package default method because it will be applied `public abstract` modifiers.
}

interface InheritPrinter1 extends Printer1 {
    // inherits `public abstract void print();`
    Object getContents() throws Exception;
}

class ImplementPrinter1 implements InheritPrinter1 {
    public void print() {
        System.out.println("ImplementPrinter1: 1");
    }
    public String getContents() { // covariant return and exception list
        return "ImplementPrinter1: 2";
    }
}

interface Printer4 {
      public    default void pu()  { System.out.println("Printer4: default public method"); } // THIS AND THE NEXT EVALUATE THE SAME
    //          default void d()   { System.out.println("Printer4: default package default method"); }

    //abstract  default void abs() { System.out.println("Printer4: default abstract method"); } // error: illegal combination of modifiers: abstract and default
    //static    default void st()  { System.out.println("Printer4: default static method"); } // error: illegal combination of modifiers: static and default
    //final     default void fin() { System.out.println("Printer4: default final method"); } // error: modifier final not allowed here

    //private   default void pri() { System.out.println("Printer4: default private method"); } // error: modifier private not allowed here
    //protected default void pro() { System.out.println("Printer4: default protected method"); } // error: modifier protected not allowed here

    // Cannot declare package default method because it will be applied modifier `public`.
}

interface InheritPrinter4 extends Printer4 {
    // inherits `public default void pu() { ... }`
}

interface OverridePrinter4 extends Printer4 {
    public default void pu()  { // Overriding is really hiding
        //super.pu(); // error: cannot find symbol `super`
        System.out.println("OverridePrinter4: default public method");
    }
}

class ConcreteOverridePrinter4 implements OverridePrinter4 {
    public static void main(String[] args) {
        new ConcreteOverridePrinter4().pu(); // prints "OverridePrinter4: default public method"
    }
}

interface HidePrinter4 extends Printer4 {
    abstract void pu();
}

class ConcreteHidePrinter4 implements HidePrinter4 {
    public void pu() { System.out.println("ConcreteHidePrinter4: public method"); }
    public static void main(String[] args) {
        new ConcreteHidePrinter4().pu(); // prints "ConcreteHidePrinter4: public method"
    }
}

// ################################################################################

abstract class AInheritPrinter4 implements Printer4 {
    // inherits `public default void pu() { ... }`
}

abstract class AOverridePrinter4 implements Printer4 {
    public void pu()  { // Overriding is really hiding
        //super.pu(); // error: cannot find symbol `super`, SAME AS IN EXAMPLE WITH CLASSES
        System.out.println("OverridePrinter4: default public method");
    }
}

class ConcreteAOverridePrinter4 extends AOverridePrinter4 {
    public static void main(String[] args) {
        new ConcreteAOverridePrinter4().pu(); // prints "OverridePrinter4: default public method"
    }
}

abstract class AHidePrinter4 implements Printer4 {
    public abstract void pu(); // remember to indicate access modifier `public`
}

class ConcreteAHidePrinter4 extends AHidePrinter4 {
    public void pu() { System.out.println("ConcreteAHidePrinter4: public method"); }
    public static void main(String[] args) {
        new ConcreteAHidePrinter4().pu(); // prints "ConcreteAHidePrinter4: public method"
    }
}


// A class may implement two interfaces that define the same field
// name, but they may not reference the field.

interface IHaveField {
    String        field = "IHaveField";
}

interface IHaveFieldToo {
    StringBuilder field = new StringBuilder("IHaveFieldToo");
}

class WhoWins implements IHaveField, IHaveFieldToo {
    public void print() {
        // error: reference to field is ambiguous, both variable field in IHaveField and variable field in IHaveFieldToo match
        System.out.println(field);
    }
}
