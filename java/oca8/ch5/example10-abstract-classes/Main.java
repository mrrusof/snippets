import java.time.LocalDateTime;

public class Main {
    public static void main(String[] aa) {
        new CountingWorker("w1").work();
    }
}

abstract class Worker {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    protected final abstract void task();

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