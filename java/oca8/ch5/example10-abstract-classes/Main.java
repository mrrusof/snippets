import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;
import java.lang.Exception;

public class Main {
    public static void main(String[] aa) {
        new CountingWorker("w1").work();
        new NumberPrinter().print();
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

    // 1. The method must have the same signature (method name and
    //    parameter type list). For example, the following declaration
    //    does not override abtract method `document` and thus fails
    //    to compile.

    // protected String document(int n) {
    //     return n + "";
    // }

    // 2. The method must be at least as accessible as the abstract
    //    method. For example, the following declaration attempts to
    //    assign weaker access privileges and thus fails to compile.

    // private int document() {
    //     return 1;
    // }

    // 3. Each exception must be covariant wrt. some exception thrown
    //    by the abstract method. For example, the following method
    //    fails to compile because abstract method does not throw
    //    Exception.

    // protected String document() throws Exception {
    //     throw new Exception("w00t");
    // }

    // This works.

    // protected String document() throws TimeoutException, MyTimeoutException {
    //     throw new TimeoutException();
    // }

    // 4. The return type must be covariant. For example, the
    // following method fails to compile because return type
    // StringBuilder is not a subtype of String.

    // protected StringBuilder document() {
    //     return new StringBuilder("1");
    // }
}
