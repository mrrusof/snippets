public class Main {

    public static void fly(Integer numMiles) {
        System.out.println("flying " + numMiles + " Integer miles");
    }
    public static void fly(int numMiles) {
        System.out.println("flying " + numMiles + " int miles");
    }

    public static void main(String... args) {
        fly(3);
    }
}
