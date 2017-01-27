import static java.lang.System.out;

public class Main {
    public static void main(String[] a) {
        new BactrianCamelPrivate();
        out.println("Number of humps: " + new BactrianCamelPublic().getNumberOfHumps());
    }
}
