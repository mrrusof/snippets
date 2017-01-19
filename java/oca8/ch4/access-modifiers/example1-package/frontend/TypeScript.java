package frontend;

import main.*;

public class TypeScript extends Javascript {
    String f = "TypeScript";

    void m(String l, String r) {
        System.out.println("m: Loc " + l + ", Decl TypeScript, Ref type " + r + ",\tObj type " + this.getClass().getSimpleName());
    }

}
