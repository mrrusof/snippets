package cs;

import server.JavaCS;

public class LangCS {
    public void whatCanYouDoWithJava() {
        System.out.println("LangCS can access the following methods of JavaCS");
        JavaCS j = new JavaCS();
        j.a();
        // j.b(); // not accessible
        // j.c(); // not accessible
        // j.d(); // not accessible
    }
}
