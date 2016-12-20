public class Chicken {
    int numEggs = 0;
    String name;
    public Chicken() {
        name = "Duke";
    }
    /*
    public void Chicken() { } // NOT A CONSTRUCTOR
    */

    public static void main(String[] args) {
        Chicken c = new Chicken();
        System.out.println(c.name);
    }
}
