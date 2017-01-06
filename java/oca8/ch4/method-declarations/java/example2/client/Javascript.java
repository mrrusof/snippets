package client;

import server.Java;

public class Javascript extends Java {

    // protected void b() {
    //     System.out.println("bla");
    // }

    public void whatCanYouDoWithJava() {
        System.out.println("Javascript can access the following methods of Java");
        Java j = new Java();
        j.a();
        // j.b(); // not accessible
        // j.c(); // not accessible
        // j.d(); // not accessible
    }
}
