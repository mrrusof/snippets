package server;

public class Groovy extends Java {
    public void whatCanYouDoWithJava() {
        System.out.println("Groovy can access the following methods of Java");
        Java j = new Java();
        j.a();
        j.b();
        j.c();
        // j.d(); // not accessible
    }
}
