import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        StringChecks.main(args);
        out.println("**************************************************");
        LocalVarsInLambdas.main(args);
        out.println("**************************************************");
        StaticVarsInLambdas.main(args);
        out.println("**************************************************");
        InstanceVarsInLambdas.main(args);
    }
}

interface CheckString {
    boolean test(String a);
}

interface CheckStrings {
    boolean test(String a, String b);
}

class StringChecks {
    static void print(List<String> l, CheckString c) {
        System.out.print("(CheckString) ");
        for(String s : l)
            if(c.test(s))
                System.out.print(s + " ");
        System.out.println();
    }

    static void print(List<String> l, String prev, CheckStrings c) {
        System.out.print("(CheckStrings) ");
        for(String s : l) {
            if(c.test(s, prev))
                System.out.print(s + " ");
            prev = s;
        }
        System.out.println();
    }

    static void print(List<String> l, String prev, Predicate<List<String>> c) {
        System.out.print("(List<String>) ");
        for(String s : l) {
            if(c.test(Arrays.asList(s, prev)))
               System.out.print(s + " ");
            prev = s;
        }
        System.out.println();
    }

    static private String two = "two";

    public static void main(String[] a) {
        List<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        print(l, s -> s == "one");
        print(l, (String s) -> s == "one");
        print(l, (String s) -> { return s == "one"; } );
        print(l, s -> { return s == "one"; } );

        out.println("**************************************************");
        print(l, "zero", (s1, s2) -> s1.charAt(0) == s2.charAt(0));
        print(l, "zero", (String s1, String s2) -> s1.charAt(0) == s2.charAt(0));
        print(l, "zero", (String s1, String s2) -> { return s1.charAt(0) == s2.charAt(0); } );
        print(l, "zero", (s1, s2) -> { return s1.charAt(0) == s2.charAt(0); } );

        out.println("**************************************************");
        print(l, "zero", (ss) -> ss.get(0).charAt(0) == ss.get(1).charAt(0));

        out.println("**************************************************");
        String one = "one";
        CheckString c = s -> s == one;
        // one = "two"; // local variables referenced from a lambda expression must be final or effectively final
        print(l, c);
        c = s -> s == two;
        two = "dos"; // this is ok
        print(l, c);
    }
}

class LocalVarsInLambdas {
  static void print(List<String> l, CheckString c) {
    for(String s : l)
      if(c.test(s))
        System.out.print(s + " ");
    System.out.println();
  }
  public static void main(String... a) {
    List<String> l = new ArrayList<String>();
    l.add("one");
    l.add("two");
    l.add("three");
    String one = "one";
    CheckString c = s -> s == one;

    print(l, c); // this works

    // the following does not work
    // one = "uno"; // compile error because we assigned one again
    // print(l, c);
  }
}

class StaticVarsInLambdas {
    static void print(List<String> l, CheckString c) {
        for(String s : l)
            if(c.test(s))
                System.out.print(s + " ");
        System.out.println();
    }
    static String one = "one";
    public static void main(String... a) {
        List<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        CheckString c = s -> s == one;
        print(l, c); // this works and prints "one"
        one = "two";
        print(l, c); // this works and prints "two"
    }
}

class InstanceVarsInLambdas {
    static void print(List<String> l, CheckString c) {
        for(String s : l)
            if(c.test(s))
                System.out.print(s + " ");
        System.out.println();
    }
    String str = "one";
    public static void main(String... a) {
        List<String> l = new ArrayList<String>();
        l.add("one");
        l.add("two");
        l.add("three");
        InstanceVarsInLambdas i = new InstanceVarsInLambdas();
        CheckString c = s -> s == i.str;
        print(l, c); // prints "one"
        i.str = "two";
        print(l, c); // prints "two"

        // does not work
        //i = null;
        //print(l, c);

        // with or without `i = null;`, this does not work
        //i = new InstanceVarsInLambdas();
        //print(l, c);

        // with or without previous two cases, this does not work
        //InstanceVarsInLambdas j = i;
        //i = j;
        print(l, c);
    }
}
