import static java.lang.System.out;

public class Tiger {
    String name;
    public static void main(String[] args) {
        Tiger t1 = new Tiger();
        Tiger t2 = new Tiger();
        Tiger t3 = t1;
        out.println("t1 == t3 = " + (t1 == t3)); // true
        out.println("t1 == t2 = " + (t1 == t2)); // false
        out.println("t1.equals(t2) = " + t1.equals(t2)); // false
    }
}
