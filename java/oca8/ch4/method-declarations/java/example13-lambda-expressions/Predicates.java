import java.util.function.Predicate;
import static java.lang.System.out;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Predicates {
    static void print(List<String> l, Predicate<String> p) {
        out.print("print: ");
        for(String s : l)
            if(p.test(s))
                out.print(s + " ");
        out.println();
    }
    public static void main(String[] aa) {
        List<String> l = Arrays.asList("one", "two", "three");
        print(l, s -> s == "one" || s == "two");
        List<String> m = new ArrayList<>();
        m.addAll(l);
        m.removeIf(s -> s == "one");
        out.println(m);
    }
}
