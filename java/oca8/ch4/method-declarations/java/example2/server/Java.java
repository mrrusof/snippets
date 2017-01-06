package server;

import static java.lang.System.out;
import client.Javascript;

public class Java extends Lang {
    public void a() {
        aa();
    }
    private void aa() {
        out.println("public");
    }
    protected void b() {
        out.println("protected");
    }
    void c() {
        out.println("package");
    }
    private void d() {
        out.println("private");
    }

    public void whatCanYouDoWithJavascript() {
        Javascript j = new Javascript();
        out.println("Java can access the following methods of Javascript");
        j.a(); // inherited
        j.b(); // inherited
        // j.c(); // cannot find symbol
        // j.d(); // cannot find symbol
    }

    public void whatCanYouDoWithGroovy() {
        Groovy j = new Groovy();
        out.println("Java can access the following methods of Groovy");
        j.a(); // inherited
        j.b(); // inherited
        j.c(); // inherited
        // j.d(); // cannot find symbol
    }

}
