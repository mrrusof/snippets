package server;

import cs.LangCS;
import static java.lang.System.out;

public class JavaCS extends LangCS {
    public void a() {
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
}
