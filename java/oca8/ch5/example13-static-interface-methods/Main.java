public class Main {
    public static void main(String[] args) {
        NumberPrinter.main(args);
    }
}

interface Printer {
    static int pageCount() {
        return 1;
    }

//     // modifier private not allowed here
//     private static int privateCount() {
//         return 0;
//     }

//     // modifier protected not allowed here
//     protected static int protectedCount() {
//         return 2;
//     }
}

class NumberPrinter implements Printer {
    public static void main(String[] args) {
        System.out.println("Printer.pageCount() = " + Printer.pageCount()); // "Printer.pageCount() = 1"
//         System.out.println("NumberPrinter.pageCount() = " + NumberPrinter.pageCount()); // cannot find symbol
//         System.out.println("pageCount() = " + pageCount()); // cannot find symbol
    }
}

interface Document1 {
    static void print() {
        System.out.println("document");
    }
}

interface Printer1 {
    static void print() {
        System.out.println("printer");
    }
}

class DocumentPrinter1 implements Document1, Printer1 { }

interface IDocumentPrinter1 extends Document1, Printer1 { }

abstract class ADocumentPrinter1 implements Document1, Printer1 { }