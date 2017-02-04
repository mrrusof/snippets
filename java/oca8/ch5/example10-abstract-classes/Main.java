import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;
import java.lang.Exception;

public class Main {
    public static void main(String[] aa) {
        new CountingWorker("w1").work();
        new NumberPrinter().print();
        new TNumberPrinter(1).print();
        new HelloPrinter().print();
        System.out.println("################################################################################");
        new PrintConstants().print();
        System.out.println("################################################################################");
        new PrintConstants().printInherited();
        System.out.println("################################################################################");
        new HideAndPrintConstants().printHidden();
        System.out.println("################################################################################");
        new HideAndPrintConstants().printFieldsThatHide();
        System.out.println("################################################################################");
        InheritingWorker.sTask2();
        InheritingWorker.sTask3();
        InheritingWorker.sTask4();
        new InheritingWorker().task2();
        new InheritingWorker().task3();
        new InheritingWorker().task4();
        System.out.println("################################################################################");
        OverridingWorker.sTask2();
        OverridingWorker.sTask3();
        OverridingWorker.sTask4();
        new OverridingWorker().task2();
        new OverridingWorker().task3();
        new OverridingWorker().task4();
        System.out.println("################################################################################");
        HidingWorker.sTask2();
        HidingWorker.sTask3();
        HidingWorker.sTask4();
        new HidingWorker().superSTask2();
        new HidingWorker().superSTask3();
        new HidingWorker().superSTask4();
    }
}

abstract class Worker {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    protected abstract void task();

    // you can do this
    //abstract protected void task();

    // you cannot do this
    //protected final abstract void task();

    // you cannot do this
    //private abstract void task();

    // you cannot do this
    //static abstract void task();

    public void work() {
        System.out.println(LocalDateTime.now() + " BEGIN " + name);
        task();
        System.out.println(LocalDateTime.now() + " END " + name);
    }
}

class CountingWorker extends Worker {

    private int limit;

    public CountingWorker(String name) {
        this(name, 10);
    }

    public CountingWorker(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    protected void task() {
        for(int i = 1; i <= limit; i++)
            System.out.println(i);
    }

}

abstract class Printer {
    abstract protected String document() throws TimeoutException;

    public void print() {
        try {
            System.out.println("printed document: " + document());
        } catch(TimeoutException e) {
            System.out.println("the document took too long to render");
        }
    }
}

class MyTimeoutException extends TimeoutException {}

class NumberPrinter extends Printer {

//     protected String document() {
//         return "1";
//     }

    // 1. The method must have the same signature (method name and
    //    parameter type list). For example, the following declaration
    //    does not override abtract method `document` and thus fails
    //    to compile.

    // protected String document(int n) {
    //     return n + "";
    // }

    //    The following declaration works fine.

    // protected String document() {
    //     return "1";
    // }

    // 2. The method must be at least as accessible as the abstract
    //    method. For example, the following declaration attempts to
    //    assign weaker access privileges and thus fails to compile.

    // private String document() {
    //     return "1";
    // }

    //    The following declaration works fine.

    // public String document() {
    //     return "1";
    // }

    // 3. Each exception must be covariant wrt. some exception thrown
    //    by the abstract method. For example, the following method
    //    fails to compile because abstract method does not throw
    //    Exception.

    // protected String document() throws Exception {
    //     throw new Exception("w00t");
    // }

    // This works.

    protected String document() throws TimeoutException, MyTimeoutException {
        throw new TimeoutException();
    }

    // 4. The return type must be covariant. For example, the
    // following method fails to compile because return type
    // StringBuilder is not a subtype of String.

    // protected StringBuilder document() {
    //     return new StringBuilder("1");
    // }
}

abstract class TimestampedPrinter extends Printer {
  protected abstract LocalDateTime getDateTime();
  public void print() {
    System.out.println("current date and time: " + getDateTime());
    super.print();
  }
}

class TNumberPrinter extends TimestampedPrinter { // CONCRETE CLASS
  protected String n;
  public TNumberPrinter(int n) {
    this.n = n + "";
  }
  protected String document() {
    return n;
  }
  public LocalDateTime getDateTime() {
      return LocalDateTime.now();
  }
}

class BlankPrinter {
  protected String document() {
    return "";
  }

  public void print() {
    System.out.println("printed document: " + document());
  }
}

abstract class HeaderPrinter extends BlankPrinter {
  protected abstract String header();
  public void print() {
    System.out.println("header of document: " + header());
    super.print();
  }
}

class HelloPrinter extends HeaderPrinter {
  protected String header() {
    return LocalDateTime.now() + "";
  }
  protected String document() {
    return "hello";
  }
}

//// modifier protected not allowed here
//protected abstract class BadWorker { }

// // modifier private not allowed here
// private abstract class BadWorker { }

// // error: illegal combination of modifiers: abstract and final
// final abstract class BadWorker { }

// Can an abstract class prescribe fields?
abstract class Constants {
    private String pri = "Constants: private field";
    String d = "Constants: package default field";
    protected String pro = "Constants: protected field";
    public String pu = "Constants: public field";

    static private String spri = "Constants: static private field";
    static String sd = "Constants: static package default field";
    static String spro = "Constants: static protected field";
    static String spu = "Constants: static public field";

    public void print() {
        System.out.println(pri);
        System.out.println(d);
        System.out.println(pro);
        System.out.println(pu);

        // error: spri has private access in Constants
        //System.out.println(spri);
        System.out.println(sd);
        System.out.println(spro);
        System.out.println(spu);
    }
}

class PrintConstants extends Constants {
    public void printInherited() {
        // error: pri has private access in Constants
        //System.out.println(pri);
        System.out.println(d);
        System.out.println(pro);
        System.out.println(pu);

        // error: spri has private access in Constants
        //System.out.println(spri);
        System.out.println(sd);
        System.out.println(spro);
        System.out.println(spu);
    }
}

class HideAndPrintConstants extends Constants {
    private String pri = "HideAndPrintConstants: private field";
    String d = "HideAndPrintConstants: package default field";
    protected String pro = "HideAndPrintConstants: protected field";
    public String pu = "HideAndPrintConstants: public field";

    static private String spri = "HideAndPrintConstants: static private field";
    static String sd = "HideAndPrintConstants: static package default field";
    static String spro = "HideAndPrintConstants: static protected field";
    static String spu = "HideAndPrintConstants: static public field";
    public void printFieldsThatHide() {
        System.out.println(pri);
        System.out.println(d);
        System.out.println(pro);
        System.out.println(pu);

        System.out.println(spri);
        System.out.println(sd);
        System.out.println(spro);
        System.out.println(spu);
    }
    public void printHidden() {
        // error: pri has private access in Constants
        //System.out.println(super.pri);
        System.out.println(super.d);
        System.out.println(super.pro);
        System.out.println(super.pu);

        // error: spri has private access in Constants
        //System.out.println(super.spri);
        System.out.println(super.sd);
        System.out.println(super.spro);
        System.out.println(super.spu);
    }
}

abstract class MultiTaskWorker {
    // error: illegal combination of modifiers: abstract and private
    //private abstract void task();

    // error: illegal combination of modifiers: abstract and final
    //final abstract void task();

    // error: illegal combination of modifiers: abstract and static
    //static abstract void task();

    abstract void task1();
    protected abstract void task2();
    public abstract void task3();
}

abstract class MultiTaskWorker2 {
    static private   void sTask1() { System.out.println("MultiTaskWorker2.sTask1"); }
    static           void sTask2() { System.out.println("MultiTaskWorker2.sTask2"); }
    static protected void sTask3() { System.out.println("MultiTaskWorker2.sTask3"); }
    static public    void sTask4() { System.out.println("MultiTaskWorker2.sTask4"); }

    private   void task1() { System.out.println("MultiTaskWorker2.task1"); }
              void task2() { System.out.println("MultiTaskWorker2.task2"); }
    protected void task3() { System.out.println("MultiTaskWorker2.task3"); }
    public    void task4() { System.out.println("MultiTaskWorker2.task4"); }
}

class InheritingWorker extends MultiTaskWorker2 { }

class OverridingWorker extends MultiTaskWorker2 {
    private   void task1() { System.out.println("OverridingWorker.task1"); } // method redeclared, not overridden
              void task2() { System.out.println("OverridingWorker.task2"); }
    protected void task3() { System.out.println("OverridingWorker.task3"); }
    public    void task4() { System.out.println("OverridingWorker.task4"); }
}

class HidingWorker extends MultiTaskWorker2 {
    static private   void sTask1() { System.out.println("HidingWorker.sTask1"); } // method redeclared, not overridden
    static           void sTask2() { System.out.println("HidingWorker.sTask2"); }
    static protected void sTask3() { System.out.println("HidingWorker.sTask3"); }
    static public    void sTask4() { System.out.println("HidingWorker.sTask4"); }

              void superSTask2() { super.sTask2(); }
    protected void superSTask3() { super.sTask3(); }
    public    void superSTask4() { super.sTask4(); }
}
