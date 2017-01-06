package exotic;

import server.Java;

public class Lisp {
    public void whatCanYouDoWithJava() {
        System.out.println("Lisp can access the following methods of Java");
        Java j = new Java();
        j.a();
        // j.b(); // not accessible
        // j.c(); // not accessible
        // j.d(); // not accessible
    }
}
