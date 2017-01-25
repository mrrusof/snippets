import static java.lang.System.out;

public class Chimpanzee extends Ape {
    public static void main(String[] a) {
        new Chimpanzee();
    }
}

class Ape extends Primate {
    public Ape() {
        out.println("Ape");
    }
}

class Primate {
    public Primate() {
        out.println("Primate");
    }
}
