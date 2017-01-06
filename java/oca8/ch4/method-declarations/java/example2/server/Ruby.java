package server;

public class Ruby {
    public void whatCanYouDoWithJava() {
        System.out.println("Ruby can access the following methods of Java");
        Java j = new Java();
        j.a();
        j.b();
        j.c();
        // j.d(); // not accessible
    }
}
