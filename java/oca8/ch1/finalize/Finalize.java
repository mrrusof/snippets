public class Finalize {
    protected void finalize() {
        System.out.println("Calling finalize");
    }
    
    public static void main(String[] args) {
        Finalize f = new Finalize();
        f = null;
        System.gc();
        try {
            Thread.sleep(4000);
        } catch(Exception e) { }
    }
}

// public class Finalize {
//     private static List objects = new ArrayList();
//     protected void finalize() {
//         objects.add(this); // gc fails because ref count for this is not zero
//         // this will not be garbage collected again because finalize() has already been executed.
//     }
// }
