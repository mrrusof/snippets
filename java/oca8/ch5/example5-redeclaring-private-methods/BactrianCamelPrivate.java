import static java.lang.System.out;

public class BactrianCamelPrivate extends Camel {
    private int getNumberOfHumps() {
        return 2;
    }
    public BactrianCamelPrivate() {
        out.println("Number of humps: " + getNumberOfHumps());
    }
}