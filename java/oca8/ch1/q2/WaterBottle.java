public class WaterBottle {
    private String brand;
    private boolean empty;
    public static void main(String[] args) {
        WaterBottle wb = new WaterBottle();
        System.out.println("Empty = " + wb.empty);
        System.out.println(", Brand = " + wb.brand);
        System.out.println(wb.brand); // this compiles
        // System.out.println(null); // but this does not compile
        String s = null;
        System.out.println(s);
        String t;
        System.out.println(t);
    }
}
