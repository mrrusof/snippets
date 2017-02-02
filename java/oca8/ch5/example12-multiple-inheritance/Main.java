public class Main {
    public static void main(String[] args) {
        //        PrintableDocument.main(args);
    }
}

interface Document {
  default void print() {
      System.out.println("document");
  }
}

interface Printer {
  default void print() {
    System.out.println("printer");
  }
}

// // class PrintableDocument inherits unrelated defaults for print() from types Document and Printer
// class PrintableDocument implements Document, Printer { }

// // class PrintableDocument inherits unrelated defaults for print() from types Document and Printer
// abstract class PrintableDocument implements Document, Printer { }

// // interface PrintableDocument inherits unrelated defaults for print() from types Document and Printer
// interface PrintableDocument extends Document, Printer { }

// class PrintableDocument implements Document, Printer {
//     public void print() {
//         System.out.println("printable document");
//     }
//     public static void main(String[] args) {
//         new PrintableDocument().print(); // "printable document"
//     }
// }

// abstract class PrintableDocument implements Document, Printer {
//     public void print() {
//         System.out.println("printable document");
//     }
// }

interface PrintableDocument extends Document, Printer {
  default void print() {
    System.out.println("printable document");
  }
}