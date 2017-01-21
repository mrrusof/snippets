public class Main {
  static void m(String s) {
    System.out.println("string");
  }
  static void m(Object o) {
    System.out.println("object");
  }
  public static void main(String... args) {
    m(""); // prints "string"
    m(42); // prints "object"

    SuperTypes.m((byte) 1); // prints "byte"
    SuperTypes.m((short) 1); // prints "int"

    ReferenceTypesAndSuperTypes.m(1); // prints "long"

    PrimitiveVsVarargs.m(1); // prints "float"
    PrimitiveVsVarargs.m(1,1); // prints "int[]"
    PrimitiveVsVarargs.m(null); // prints "int[]"

    PrimitiveVsArray.m(1); // prints "float"
    //PrimitiveVsArray.m(1,1); // no suitable method found for m(int,int)
    PrimitiveVsArray.m(null); // prints "int[]"

    RefTypeVsVarArgs.m((byte) 1); // prints "varargs"
    RefTypeVsVarArgs.m(1); // prints "Integer"
    //RefTypeVsVarArgs.m(null); // reference to m is ambiguous

    // OnlyOneConversion.m((byte) 1); // byte cannot be converted to Integer

    AutoboxingPrimitiveToObject.m((byte) 1); // actual gets autoboxed to Byte
 }
}

class SuperTypes {
    static void m(byte n) {
        System.out.println("byte");
    }
    static void m(int n) {
        System.out.println("int");
    }
}

class ReferenceTypesAndSuperTypes {
    static void m(long n) {
        System.out.println("long");
    }
    static void m(Integer n) {
        System.out.println("Integer");
    }
}

class PrimitiveVsVarargs {
  static void m(float n) {
    System.out.println("float");
  }
  static void m(int... nn) {
    System.out.println("varargs");
  }
}

class PrimitiveVsArray {
  static void m(float n) {
    System.out.println("float");
  }
  static void m(int[] nn) {
    System.out.println("int[]");
  }
}

class RefTypeVsVarArgs {
    static void m(Integer n) {
        System.out.println("Integer");
    }
    static void m(int... nn) {
        System.out.println("varargs");
    }
}

class OnlyOneConversion {
    static void m(Integer n) {
        System.out.println("Integer");
    }
}

class AutoboxingPrimitiveToObject {
    static void m(Object o) {
        System.out.println("Object: " + o.getClass().getSimpleName());
    }
}
