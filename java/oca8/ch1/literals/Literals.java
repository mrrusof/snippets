import static java.lang.System.out;

public class Literals {
    public static void main(String[] args) {
        System.out.println(3000000000L);
        System.out.println(0b11);
        System.out.println(017);
        System.out.println(0x1f);
        System.out.println(1_000_000);
        // System.out.println(_1000.00); // does not compile
        // out.println(1000.00_); // does not compile
        // out.println(1000_.00); // does not compile
        out.println(1_00_0.0_0); 
    }
}