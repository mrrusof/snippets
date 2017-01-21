public class Main {
    public static void main(String[] args) {
        Immutable.main(args);
        NotImmutable.main(args);
        ConvertedToImmutable.main(args);
    }
}

class Immutable {
    int v;
    Immutable(int v) {
        this.v = v;
    }
    public int getV() {
        return v;
    }
    public String toString() {
        return v + "";
    }

    public static void main(String... args) {
        Immutable i = new Immutable(2);
        System.out.println("i = " + i);
    }
}

class NotImmutable {
    StringBuilder s;
    NotImmutable(StringBuilder s) {
        this.s = s;
    }
    public String toString() {
        return s.toString();
    }

    public static void main(String... args) {
        StringBuilder s = new StringBuilder("hola");
        NotImmutable ni = new NotImmutable(s);
        System.out.println("ni = " + ni);
        s.append(" mundo");
        System.out.println("ni = " + ni);
    }
}


class ConvertedToImmutable {
    StringBuilder s;
    ConvertedToImmutable(StringBuilder s) {
        this.s = new StringBuilder(s);
    }
    public String toString() {
        return s.toString();
    }

    public static void main(String... args) {
        StringBuilder s = new StringBuilder("hola");
        ConvertedToImmutable ni = new ConvertedToImmutable(s);
        System.out.println("ni = " + ni);
        s.append(" mundo");
        System.out.println("ni = " + ni);
    }
}
