import java.lang.Exception;

public class Main {
    public static void main(String[] args) {
        new CatchOrder().exec();
        new ExceptionMasking().exec();
        new MustThrowCheckedException().exec();
        GoodChildClass.main(args);
        new Work().work();
    }
}

class Work {
    public void work() {
        // try // error: '{' expected
        //     System.out.println("w00t");
        // catch (Exception e) // error: '{' expected
        //     System.out.println("catch");

        try {
            System.out.println("try1");
            throw new Exception("w00t");
        } catch (Exception e) {
            System.out.println("catch1");
        } finally {
            System.out.println("finally1");
        }

        try {
            System.out.println("try2");
            throw new Exception("w00t");
        } catch (Exception e) {
            System.out.println("catch2");
        }

        try {
            System.out.println("try3");
        } finally {
            System.out.println("finally3");
        }

        try {
            System.out.println("exiting now");
            System.exit(0);
        } finally {
            System.out.println("unreachable statement");
        }
    }
}

class TheParent extends RuntimeException { }
class TheChild extends TheParent { }

class CatchOrder {
    public void exec() {
        try {
            throw new TheParent();
        } catch(TheChild e) {
            System.out.println("TheChild");
        } catch(TheParent e) {
            System.out.println("TheParent");
        } catch(RuntimeException e) {
            System.out.println("RuntimeException");
        }

        try {
            throw new TheParent();
        } catch(TheParent e) {
            System.out.println("TheParent");
        // } catch(TheChild e) { // error: exception TheChild has already been caught
        //     System.out.println("TheChild");
        } catch(RuntimeException e) {
            System.out.println("RuntimeException");
        }
    }
}

class ExceptionMasking {
    public void exec() {
        try {
            try {
                throw new RuntimeException();
            } catch(RuntimeException e) {
                throw new TheParent();
            } finally {
                throw new TheChild();
            }
        } catch(TheChild e) {
            System.out.println("caught exception thrown by finally clause");
        }
    }
}

class IMustBeThrown extends Exception { }

class MustThrowCheckedException {
    public void exec() {
        // try {
        //     System.out.println("I don't throw any exceptions");
        // } catch(IMustBeThrown e) { // error: exception IMustBeThrown is never thrown in body of corresponding try statement
        //     System.out.println("caught IMustBeThrown");
        // }
    }
}

class ParentClass {
    public void task() throws IMustBeThrown { }
}

// class BadChildClass extends ParentClass {
//     public void task() throws Exception { } // error: task() in ChildClass cannot override task() in ParentClass, overridden method does not throw Exception
// }

class GoodChildClass extends ParentClass {
    public void task() throws RuntimeException { } // this is fine!
    public static void main(String[] args) {
        new GoodChildClass().task();
    }
}
