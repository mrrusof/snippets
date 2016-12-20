public class Name {
    String first = "Theodore";
    String last = "Moose";
    String full = first + " " + last;

    public static void main(String[] args) {
        Name n = new Name();
        System.out.println("n.full = " + n.full);
        InstanceInitializer m = new InstanceInitializer();
        System.out.println("m.full = " + m.full);
    }
}

class InstanceInitializer {
    String first;
    String last;
    String full;

    // instance initializer
    {
        first = "Theodore";
        last = "Moose";
        full = first + " " + last;
    }
}
