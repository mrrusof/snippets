public class Main {
    public static void main(String[] args) {
        System.out.println("hola");
    }
}


class Mammal {
    public Mammal(int age) {
        System.out.println("Mammal");
    }
}
class Platypus extends Mammal {
    public Platypus() {
        System.out.println("Platypus");
    }
    public static void main(String[] args) {
        new Mammal(5);
    }
}
