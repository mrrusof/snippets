import cs.LangCS;
import server.Lang;
import server.Ruby;
import server.Groovy;
import client.Javascript;
import exotic.Lisp;
import server.Java;

public class Main {
    public static void main(String[] args) {
        new LangCS().whatCanYouDoWithJava();
        new Lang().whatCanYouDoWithJava();
        new Ruby().whatCanYouDoWithJava();
        new Groovy().whatCanYouDoWithJava();
        new Javascript().whatCanYouDoWithJava();
        new Lisp().whatCanYouDoWithJava();
        new Java().whatCanYouDoWithJavascript();
        new Java().whatCanYouDoWithGroovy();
    }
}
