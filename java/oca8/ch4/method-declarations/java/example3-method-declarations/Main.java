import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        System.out.println("this works");
        walk8();
        out.println("task_byte3() = " + task_byte3());
        out.println("task_byte4() = " + task_byte4());
        varargs1(null);
        varargs1(new int[] {1,2,3});
        //varargs1([]); // does not compile
        varargs1(1,2,3);
        varargs2(null);
        varargs2(new int[] {1,2,3});
        // varargs2(1,2,3); // does not compile
        varargs3(1);
        // varargs3(1, null); // null pointer exception
        // varargs3(1, null, null); // method varargs3 in class Main cannot be applied to given types;
        varargs4(1, new Object[] {null, null});
    }

    public void walk1() { }
    public final void walk2() { }
    public static final void walk3() { }
    public final static void walk3b() { }
    public final static void walk4() { }
    // public modifier void walk5() { } // does not compile because modifier is not an optional specifier
    // public void final walk6() { } // does not compile because modifier is after return type
    // optional specifiers can go before the access modifier
    final public void walk7() { }
    static final public void walk8() { System.out.println("walk8"); }
    static public void walk9() { out.println("walk9"); }
    synchronized public void walk10() { }
    final public void walk11() { }
    final public static void walk12() { }
    static public final void walk13() { }

    public void walk2_2() { return; }
    public String walk2_3() { return ""; }
    // public String walk2_4() { } // inane
    //public  walk2_5() { } // inane
    // String walk2_6(int a) { if (a == 4) return ""; } // not all paths return a string

    public byte task_byte() { return 1; }
    public byte task_byte2() {
        byte b = 2;
        b = 1;
        return b;
    }
    static public byte task_byte3() { return 1 + 1; } // compile error because type of 1 + 1 is int
    static public byte task_byte4() {
        byte b = 1 + 1; // there is no problem here
        // b = b + b; // but there is a 'possible lossy conversion from int to byte' here, w00t?
        Object o = b + b;
        out.println("o.getClass().getName() = " + o.getClass().getName());
        b = (int) 1; // no problem here
        b = (short) 1; // no problem here
        // b = (long) 1; // this is a compile error
        return b;
    }
    // static public byte task_byte5() {
    //     byte b = 1;
    //     return b + b; // error, type of expression `b+b` is int
    // }
    static public short task_byte6() { return (short) 1;  }
    static public short task_byte7() { return (short) (1 + 1); }
    static public short task_byte8() { return (int) (1 + 1); }
    static public short task_byte9() { return (byte) 1 + (byte) 1; }
    static public short task_short1() {
        return 1 + 1;
    }
    static public short task_short2() {
        short s = 1 + 1;
        return s;
    }
    // static public short task_short3() {
    //     short s = 1;
    //     return s + s; // compile error, type of expression `s+s` is int
    // }
    static public Object task_object1() { return "hola"; }
    static public String task_string1() { return (String) (Object) "hola"; }
    //static public float task_float1() { return 1.0; }; // compile error, type of 1.0 is double
    static public float task_float2() { return (float) 1.0; }
    static public float task_float3() { return 1.0f; }

    static public void varargs1(int... nn) {
        out.print("varargs1: ");
        if(nn == null)
            out.print("nn is null!");
        else
            for(int n : nn)
                out.print(n + ",");
        out.println();
    }
    static public void varargs2(int[] nn) {
        out.print("varargs1: ");
        if(nn == null)
            out.print("nn is null!");
        else
            for(int n : nn)
                out.print(n + ",");
        out.println();
    }
    static public void varargs3(int m, int... n) {
        out.println("varargs3: n.length = " + n.length);
    }
    static public void varargs4(int m, Object... o) {
        out.println("varargs4: o.length = " + o.length);
    }
}
