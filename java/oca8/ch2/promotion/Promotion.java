public class Promotion {
    public static void main(String... args) {
        System.out.println("promote int to double " + (1 + 1.0));
        Object o = 1 + 1.0;
        System.out.println("type of 1 + 1.0 = " + o.getClass().getName());

        System.out.println("promote int to long " + (1 + 1L));
        o = 1 + 1L;
        System.out.println("type of 1 + 1L = " + o.getClass().getName());

        byte b = 1;
        System.out.println("promote byte to int " + (b + 1));
        o = b + 1;
        System.out.println("type of b + 1 = " + o.getClass().getName());

        short s = 3;
        System.out.println("promote byte to integer, because byte and short are promoted to int before anything else " + (s + b));
        o = s + b;
        System.out.println("type of s + b = " + o.getClass().getName());

        System.out.println("promote short to integer, because short is promoted for +: " + (s + s));
        o = s + s;
        System.out.println("type of s + s = " + o.getClass().getName());

        System.out.println("promotion of short does not happen for operator --: " + --s);
        o = --s;
        System.out.println("type of --s = " + o.getClass().getName());

        System.out.println("promotion of short does not happen for operator ++ either: " + ++s);
        o = ++s;
        System.out.println("type of ++s = " + o.getClass().getName());

        System.out.println("Java promotes byte for op + too: " + (b + b));
        o = b + b;
        System.out.println("type of b + b = " + o.getClass().getName());

        System.out.println("And also, Java keeps the type of byte for op --: " + b--);
        o = b--;
        System.out.println("type of b-- = " + o.getClass().getName());

        long l = 1;
        System.out.println("promote long to double " + (l + 1.0));
        o = l + 1.0;
        System.out.println(o.getClass().getName());

        char c = 'a';
        System.out.println("What about chars? They get promoted to integer like byte and short too. " + (c + b));
        o = c + b;
        System.out.println("type of c + b = " + o.getClass().getName());

        float f = 2.1;
        System.out.println("f = " + f);
    }
}
