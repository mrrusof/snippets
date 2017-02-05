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
        new InheritFields().print();
        System.out.println("################################################################################");
        new InheritFields().printInherited();
        System.out.println("################################################################################");
        new HideFields().printHiddenFields();
        System.out.println("################################################################################");
        new HideFields().printFieldsThatHide();
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
abstract class Fields {
    private   String pri = "Fields: private field";
              String d   = "Fields: package default field";
    protected String pro = "Fields: protected field";
    public    String pu  = "Fields: public field";

    final private   String fpri = "Fields: final private field";
    final           String fd   = "Fields: final package default field";
    final protected String fpro = "Fields: final protected field";
    final public    String fpu  = "Fields: final public field";

    static private String spri = "Fields: static private field";
    static         String sd   = "Fields: static package default field";
    static         String spro = "Fields: static protected field";
    static         String spu  = "Fields: static public field";

    final static private String fspri = "Fields: final static private field";
    final static         String fsd   = "Fields: final static package default field";
    final static         String fspro = "Fields: final static protected field";
    final static         String fspu  = "Fields: final static public field";

    public void print() {
        System.out.println("Fields: " + pri);
        System.out.println("Fields: " + d);
        System.out.println("Fields: " + pro);
        System.out.println("Fields: " + pu);

        System.out.println("Fields: " + fpri);
        System.out.println("Fields: " + fd);
        System.out.println("Fields: " + fpro);
        System.out.println("Fields: " + fpu);

        System.out.println("Fields: " + spri);
        System.out.println("Fields: " + sd);
        System.out.println("Fields: " + spro);
        System.out.println("Fields: " + spu);

        System.out.println("Fields: " + fspri);
        System.out.println("Fields: " + fsd);
        System.out.println("Fields: " + fspro);
        System.out.println("Fields: " + fspu);
    }
}

class InheritFields extends Fields {
    public void printInherited() {
        //System.out.println("InheritFields: " + pri); // error: pri has private access in Fields
        System.out.println("InheritFields: " + d);
        System.out.println("InheritFields: " + pro);
        System.out.println("InheritFields: " + pu);

        //System.out.println("InheritFields: " + fpri); // error: fpri has private access in Fields
        System.out.println("InheritFields: " + fd);
        System.out.println("InheritFields: " + fpro);
        System.out.println("InheritFields: " + fpu);

        //System.out.println("InheritFields: " + spri); // error: spri has private access in Fields
        System.out.println("InheritFields: " + sd);
        System.out.println("InheritFields: " + spro);
        System.out.println("InheritFields: " + spu);

        //System.out.println("InheritFields: " + fspri); // error: fspri has private access in Fields
        System.out.println("InheritFields: " + fsd);
        System.out.println("InheritFields: " + fspro);
        System.out.println("InheritFields: " + fspu);
    }
}

class HideFields extends Fields {

              String d   = "HideFields: package default field";
    protected String pro = "HideFields: protected field";
    public    String pu  = "HideFields: public field";

    final           String fd   = "HideFields: final package default field";
    final protected String fpro = "HideFields: final protected field";
    final public    String fpu  = "HideFields: final public field";

    static         String sd   = "HideFields: static package default field";
    static         String spro = "HideFields: static protected field";
    static         String spu  = "HideFields: static public field";

    final static         String fsd   = "HideFields: final static package default field";
    final static         String fspro = "HideFields: final static protected field";
    final static         String fspu  = "HideFields: final static public field";

    public void printFieldsThatHide() {
        System.out.println("HideFields: " + d);
        System.out.println("HideFields: " + pro);
        System.out.println("HideFields: " + pu);

        System.out.println("HideFields: " + fd);
        System.out.println("HideFields: " + fpro);
        System.out.println("HideFields: " + fpu);

        System.out.println("HideFields: " + sd);
        System.out.println("HideFields: " + spro);
        System.out.println("HideFields: " + spu);

        System.out.println("HideFields: " + fsd);
        System.out.println("HideFields: " + fspro);
        System.out.println("HideFields: " + fspu);
    }
    public void printHiddenFields() {
        System.out.println("HideFields: " + super.d);
        System.out.println("HideFields: " + super.pro);
        System.out.println("HideFields: " + super.pu);

        System.out.println("HideFields: " + super.fd);
        System.out.println("HideFields: " + super.fpro);
        System.out.println("HideFields: " + super.fpu);

        System.out.println("HideFields: " + super.sd);
        System.out.println("HideFields: " + super.spro);
        System.out.println("HideFields: " + super.spu);

        System.out.println("HideFields: " + super.fsd);
        System.out.println("HideFields: " + super.fspro);
        System.out.println("HideFields: " + super.fspu);
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

    static final private   void sfTask1() { System.out.println("MultiTaskWorker2.sTask1"); }
    static final           void sfTask2() { System.out.println("MultiTaskWorker2.sTask2"); }
    static final protected void sfTask3() { System.out.println("MultiTaskWorker2.sTask3"); }
    static final public    void sfTask4() { System.out.println("MultiTaskWorker2.sTask4"); }

    private   void task1() { System.out.println("MultiTaskWorker2.task1"); }
              void task2() { System.out.println("MultiTaskWorker2.task2"); }
    protected void task3() { System.out.println("MultiTaskWorker2.task3"); }
    public    void task4() { System.out.println("MultiTaskWorker2.task4"); }

    final private   void fTask1() { System.out.println("MultiTaskWorker2.task1"); }
    final           void fTask2() { System.out.println("MultiTaskWorker2.task2"); }
    final protected void fTask3() { System.out.println("MultiTaskWorker2.task3"); }
    final public    void fTask4() { System.out.println("MultiTaskWorker2.task4"); }
}

class InheritingWorker extends MultiTaskWorker2 { }

class OverridingWorker extends MultiTaskWorker2 {
    private   void task1() { System.out.println("OverridingWorker.task1"); } // method redeclared, not overridden
              void task2() { System.out.println("OverridingWorker.task2"); }
    protected void task3() { System.out.println("OverridingWorker.task3"); }
    public    void task4() { System.out.println("OverridingWorker.task4"); }

    final private   void fTask1() { System.out.println("MultiTaskWorker2.task1"); } // method redeclared, not overridden
    //final           void fTask2() { System.out.println("MultiTaskWorker2.task2"); } // error: fTask2() in OverridingWorker cannot override fTask2() in MultiTaskWorker2
    //final protected void fTask3() { System.out.println("MultiTaskWorker2.task3"); } // error: fTask3() in OverridingWorker cannot override fTask3() in MultiTaskWorker2
    //final public    void fTask4() { System.out.println("MultiTaskWorker2.task4"); } // error: fTask4() in OverridingWorker cannot override fTask4() in MultiTaskWorker2
}

class HidingWorker extends MultiTaskWorker2 {
    static private   void sTask1() { System.out.println("HidingWorker.sTask1"); } // method redeclared, not overridden
    static           void sTask2() { System.out.println("HidingWorker.sTask2"); }
    static protected void sTask3() { System.out.println("HidingWorker.sTask3"); }
    static public    void sTask4() { System.out.println("HidingWorker.sTask4"); }

    static final private   void sfTask1() { System.out.println("HidingWorker.sTask1"); } // method redeclared, not overridden
    //static final           void sfTask2() { System.out.println("HidingWorker.sTask2"); } // error: sfTask2() in HidingWorker cannot override sfTask2() in MultiTaskWorker2
    //static final protected void sfTask3() { System.out.println("HidingWorker.sTask3"); } // error: sfTask3() in HidingWorker cannot override sfTask3() in MultiTaskWorker2
    //static final public    void sfTask4() { System.out.println("HidingWorker.sTask4"); } // error: sfTask4() in HidingWorker cannot override sfTask4() in MultiTaskWorker2

              void superSTask2() { super.sTask2(); }
    protected void superSTask3() { super.sTask3(); }
    public    void superSTask4() { super.sTask4(); }
}

abstract class HidingWorkerByAbstract extends MultiTaskWorker2 {

    // YOU CANNOT HIDE A STATIC METHOD BY ABSTRACT METHOD
    //abstract static void sTask2(); // error: illegal combination of modifiers: abstract and static
    //abstract        void sTask2(); // error: sTask2() in HidingWorkerByAbstract cannot override sTask2() in MultiTaskWorker2, overridden method is static

    // YOU CANNOT HIDE A FINAL METHOD EITHER
    abstract           void fTask2(); // error: fTask2() in HidingWorkerByAbstract cannot override fTask2() in MultiTaskWorker2, overridden method is final

    // INSTANCE METHODS ARE OK
    abstract           void task2();
    abstract protected void task3();
    abstract public    void task4();
}
