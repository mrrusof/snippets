package server;

public class Lang {
    public void whatCanYouDoWithJava() {
        System.out.println("Lang can access the following methods of Java");
        Java j = new Java();
        j.a();
        j.b();
        j.c();
        // j.d(); // not accessible
    }
}
