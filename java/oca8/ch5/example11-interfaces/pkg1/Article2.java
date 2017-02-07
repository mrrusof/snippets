package pkg1;

public interface Article2 {
    // error: modifier private not allowed here
    //private   String pri = "Article2: private field";
              String d   = "Article2: static final public field (not instance package default)";
    // error: modifier protected not allowed here
    //protected String pro = "Article2: protected field";
    public    String pu  = "Article2: static final public field (not instance public)";

    // error: modifier private not allowed here
    //static private   String spri = "Article2: static private field";
    static           String sd   = "Article2: static final public field (not static package default)";
    // error: modifier protected not allowed here
    //static protected String spro = "Article2: static protected field";
    static public    String spu  = "Article2: static final public field (not just static public)";

    // error: modifier private not allowed here
    //static final private   String sfpri = "Article2: static final private field";
    static final           String sfd   = "Article2: static final public field (not static final package default) ";
    // error: modifier protected not allowed here
    //static final protected String sfpro = "Article2: static final protected field";
    static final public    String sfpu  = "Article2: static final public field";

    // error: = expected
    // String c;
    // static { c = "assignment won't compile"; }

    static void print() {
        System.out.println("Article2: " + sfpu);
    }
}
