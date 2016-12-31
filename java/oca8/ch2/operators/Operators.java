import java.util.ArrayList;

public class Operators {

    public void FinalArg(final String finalArg) {
        switch("hola") {
        // case finalArg: // this does not compile
        //     System.out.println(finalArg);
        default:
            System.out.println("default");
        }
    }

    public static void main(String... args) {

        int x = 0, y = 0;
        System.out.println(x++ - y++ + ++x);

        x = 0b001;
        y = 0b011;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("x & y = " + (x & y));
        System.out.println("x | y = " + (x | y));
        System.out.println("x ^ y = " + (x ^ y));

        System.out.println("(y == 3 ? \"yes\" : \"no\") = " + (y == 3 ? "yes" : "no"));
        System.out.println("(y == 1 ? \"yes\" : \"no\") = " + (y == 1 ? "yes" : "no"));

        x = -2;
        System.out.println("x = " + x);
        System.out.println("x = " + (x>>1));
        System.out.println("x = " + (x>>>1));

        x = 0;
        System.out.println("(--x + ++x) = " + (--x + ++x));

        x = 0B100;
        System.out.println("x = " + x);
        System.out.println("~x = " + ~x);
        System.out.println("(x << 1) = " + (x << 1));

        System.out.println("1 / 2 = " + 1 / 2);
        System.out.println("9 / 3 = " + 9 / 3);
        System.out.println("9 % 3 = " + 9 % 3);
        System.out.println("10 / 3 = " + 10 / 3);
        System.out.println("10 % 3 = " + 10 % 3);
        System.out.println("11 / 3 = " + 11 / 3);
        System.out.println("11 % 3 = " + 11 % 3);
        System.out.println("12 / 3 = " + 12 / 3);
        System.out.println("12 % 3 = " + 12 % 3);
        System.out.println("-9 / 3 = " + -9 / 3);
        System.out.println("-9 % 3 = " + -9 % 3);
        System.out.println("-10 / 3 = " + -10 / 3);
        System.out.println("-10 % 3 = " + -10 % 3);
        System.out.println("-11 / 3 = " + -11 / 3);
        System.out.println("-11 % 3 = " + -11 % 3);
        System.out.println("-12 / 3 = " + -12 / 3);
        System.out.println("-12 % 3 = " + -12 % 3);

        System.out.println("9 / -3 = " + 9 / -3);
        System.out.println("9 % -3 = " + 9 % -3);
        System.out.println("10 / -3 = " + 10 / -3);
        System.out.println("10 % -3 = " + 10 % -3);

        System.out.println("-15 / 4 = " + -15 / 4);
        System.out.println("-15 % 4 = " + -15 % 4);

        x = 0;
        System.out.println("(++x + x--) =" + (++x + x--));
        // System.out.println("(++ x--) = " + (++ x --)); // does not compile because x-- is not a variable.

        // textbook examples
        // x = 1.0; // does not compile
        // short yy = 1921222; // does not compile
        // int z = 9f; // does not compile
        // long t = 192301398193810323; // does not compile
        // short xx = 1;
        // short yy = 3;
        // short zz = xx * yy; // does not compile

        short z = 100;
        x = z;
        System.out.println("x = " + x);

        // z = x; // does not compile
        x = 200;
        z = (short) x;
        System.out.println("z = " + z);

        // casting

        x = (int) 1.0;
        // Casting short to int truncates sequence of bits.
        short yy = 1921222 & 0b1111111111111111;
        System.out.println("yy = " + yy);
        yy = (short) 1921222;
        System.out.println("yy = " + yy);
        // Casting float to int truncates decimals.
        int zz = (int) 9.25f;
        System.out.println("zz = " + zz);
        long t = 192301398193810323L;
        // cast shorts or else...
        short xx = 1;
        yy = 3;
        short uu = (short) (xx * yy);
        // Java automatically promotes char to int
        char thechar = 'a';
        System.out.println("thechar + 'b' = " + (thechar + 'b'));

        // compound assignment
        // int vv *= 1; // does not compile.
        uu *= 3; // does not require cast!
        System.out.println("uu = " + uu);
        t = 3;
        x = 1;
        // x = x * t; // does not compile
        x *= t;
        System.out.println("x = " + x);
        {
            long aa = 4;
            short bb = 3;
            bb *= aa;
            System.out.println("bb = " + bb);
        }
        {
            long aa = 3;
            short bb = 4;
            byte cc = 2;
            cc *= bb *= aa;
            System.out.println("cc = " + cc);
        }
        {
            // textbook example
            long a = 5;
            long b = a = 3;
            System.out.println("a = " + a);
            System.out.println("b = " + b);
        }

        // relational operators
        // System.out.println("(true > false) = " + (true > false)); // does not compile
        // System.out.println("(true - false) = " + (true - false)); // does not compile
        // System.out.println("(true % false) = " + (true % false)); // does not compile
        // System.out.println("(true =< false) = " + (true =< false)); // does not compile
        System.out.println("\"hola\" instanceof String = " + ("hola" instanceof String));
        System.out.println("\"hola\" instanceof Object = " + ("hola" instanceof Object));
        //        System.out.println("\"hola\" instanceof List = " + ("hola" instanceof ArrayList)); // does not compile
        //        System.out.println("\"hola\" instanceof Iterable = " + ("hola" instanceof Iterable)); // does not compile either, what's the point?

        // logical operators...
        { 
            boolean a = false;
            boolean b = true;
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("a & (b = false) = " + (a & (b = false)));
            System.out.println("b = " + b); // b = false
            b = true;
            System.out.println("b = " + b);
            System.out.println("a && (b = false) = " + (a && (b = false)));
            System.out.println("b = " + b); // b = true

            System.out.println("a ^ b = " + (a ^ b));

            boolean c = true || c; // this compiles
        }

        // equality operator
        {
            String a = new String("hola");
            String b = new String("hola");
            System.out.println("(constructor \"hola\") a == b = " + (a == b));
        }
        {
            String a = "hola";
            String b = "hola";
            System.out.println("(literal \"hola\") a == b = " + (a == b));
        }
        {
            Boolean aa = new Boolean(true);
            Boolean bb = new Boolean(true);
            System.out.println("(constructor true) aa == bb = " + (aa == bb));
            Boolean cc = bb;
            System.out.println("(constructor true) cc == bb = " + (cc == bb));
        }
        {
            Boolean aa = true;
            Boolean bb = true;
            System.out.println("(boxing of literal true) aa == bb = " + (aa == bb));
            aa = false;
            System.out.println("aa = " + aa);
            System.out.println("bb = " + bb);
            System.out.println("aa == bb = " + (aa == bb));
        }
        {
            Integer aaa = 1;
            Integer bbb = 1;
            System.out.println(aaa == bbb);
        }
        
        // if-then-else
        {
            int xxx = 1;
            // if(x) // does not compile, because x is not boolean.
            //     System.out.println(x);
        }
        {
            int xxx = 5;
            // if(x = 5) // does not compile, because expression x = 5 is not boolean.
            //     System.out.println(x);
        }

        // ternary operator
        {
            int xxx = 1;
            int yyy = xxx == 1 ? 2 : 3;
            System.out.println("yyy = " + yyy);
            System.out.println(xxx == 1 ? "hola" : false); // second and third operands may not be of the same type.
            System.out.println(xxx == 2 ? "hola" : false);
            //            int zzz = (xxx == 1 ? 1 : false); // here, type checker detects that type of second operand cannot be coerced to int.
            int zzz = xxx == 1 ? 1 : (int)2.5f; // this works...
        }
        {
            int a = 1;
            int b = 1;
            final int c = a < 10 ? a++ : b++;
            System.out.println(a + "," + b);
        }

        // switch statement...
        {
            int a = 1;
            switch(a) {
            case 1:
                System.out.println("hola");
            case 2:
                System.out.println("mundo");
                break;
            default:
                System.out.println("bye");
            }
        }
        {
            int a = 2;
            switch(a) {
            case 1:
                System.out.println("eaeaea");
            case 2:
                System.out.println("EAEAEA");
                break;
            default:
                System.out.println("bye");
            }
        }
        {
            int a = 1;
            final int b = 2;
            switch(a) {
            case b: // the expression in case statement must be a literal, an enum or a final var.
                System.out.println(0);
                break;
            case 1:
                System.out.println(1);
                break;
            case 3:
                System.out.println(2);
                break;
            default:
                System.out.println("default");
            }
        }

        System.out.println("separator");

        {
            int a = 1;
            final int b = 2;
            switch(a) {
            case 1:
                System.out.println(1);
            case b:
                System.out.println(b);
                break;
            default:
                System.out.println("default");
            }
        }

        System.out.println("separator");

        {
            int a = 1;
            switch(a) {
            // case 1L: // type of case expression must be the same as type of switch expression.
            //     System.out.println(1);
            //     break;
            default:
                System.out.println("default");
            }

            short b = 1;
            switch(b) {
            case 1: // this works, so switch expression is promoted to int
                System.out.println(b);
                break;
            default:
                System.out.println("default");
            }
        }

    }
}
