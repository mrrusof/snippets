package main;

import backend.*;
import frontend.*;

public class Main {
    public static void main(String... args) {
        new Cpp().callPackageMethods();
        new Java().callPackageMethods();
        new Groovy().callPackageMethods();
        new Ruby().callPackageMethods();

        new Javascript().callPackageMethods();

        new Java().accessPackageFields();
    }

    public static void printFieldAccess(String l, String d, String r, String o) {
        String tab = "\t";
        if(r.length() <= 4) tab = "\t\t";
        System.out.println("f: Loc " + l + ", Decl " + d + ", Ref type " + r + "," + tab + "Obj type " + o);
    }
}
