import java.lang.Exception;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new CatchOrder().exec();
        new ExceptionMasking().exec();
        new MustThrowCheckedException().exec();
        GoodChildClass.main(args);
        CatchCheckedAndUncheckedExceptions.main(args);
        Errors.thisIsFine();
        //Errors.givesAnError();
        System.out.println("################################################################################");
        //new ExceptionInInitializerErrorExample();
        //new StackOverflowExample().sux();
        //new MissingClass().task();
        FileNotFoundExceptionEx.failAtOpenningFile();
        UncheckedExceptionsEx.task();
        System.out.println("################################################################################");
        //        new Work().work();
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

// Handle or declare rule

// It is possible to catch checked and unchecked exceptions.

class CheckedException extends Exception { }
class UncheckedException extends RuntimeException { }

class CatchCheckedAndUncheckedExceptions {
    public static void main(String[] args) {
        try {
            throw new CheckedException();
        } catch(CheckedException e) {
            System.out.println("caught a checked exception");
        }
        try {
            throw new UncheckedException();
        } catch(UncheckedException e) {
            System.out.println("caught an unchecked exception");
        }

    }
}

// Only checked exceptions are required to be handled or declared to be thrown.

// class WhatMustIHandleOrDeclare {
//     public static void checked() {
//         throw new CheckedException(); // error: unreported exception CheckedException; must be caught or declared to be thrown
//     }
//     public static void unchecked() {
//         throw new UncheckedException(); // this line causes no compiler error
//     }
// }

// Errors may not be caught and may not be declared to be thrown.

class Errors {
    public static void givesAnError() {
        throw new Error(); // line does not cause compiler error
    }
    public static void thisIsFine() {
        try {
            throw new Error();
        } catch(Error e) {
            System.out.println("caught the error, but shouldn't have");
        }
    }
}

// A given checked exception may not be caught when the try block never throws the exception.

class TryCatchingCheckedNeverThrown {
    public static void task() {
        // try {
        //     System.out.println("I never throw IOException");
        // } catch(IOException e) { // error: exception IOException is never thrown in body of corresponding try statement
        //     System.out.println("I am unreachable");
        // }
    }
}

// Relevant errors for the exam

class ExceptionInInitializerErrorExample {
    static {
        int[] nn = new int[3];
        nn[-1] = 1;
    }
}

class StackOverflowExample {
    public void sux() {
        sux();
    }
}

// NoClassDefFoundException: just delete a class and try to run your program


// Relevant checked exceptions

class FileNotFoundExceptionEx {
    public static void failAtOpenningFile() {
        try {
            FileInputStream f = new FileInputStream("/hola");
        } catch(FileNotFoundException e) {
            System.out.println("tada!, file was not found");
        }
        try {
            FileReader f = new FileReader("/hola");
        } catch(FileNotFoundException e) {
            System.out.println("tada!, file is still not found sherlock");
        }
        try {
            FileReader f = new FileReader("./Main.java");
            while(f.ready())
                System.out.print((char)f.read());
            f.close();
            f.ready();
        } catch(FileNotFoundException e) {
            System.out.println("unreachable");
        } catch(IOException e) {
            System.out.println("tada! the file was already closed! " + e);
        }
    }
}

// Relevant unchecked exceptions

class UncheckedExceptionsEx {
    public static void task() {
        try {
            int i = 1 / 0;
        } catch(ArithmeticException e) {
            System.out.println(e + "");
        }
        try {
            int[] nn = new int[3];
            nn[-1] = 1;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e + "");
        }
        try {
            Number n = new Integer(1);
            Float f = (Float) n;
        } catch(ClassCastException e) {
            System.out.println(e + "");
        }
        try {
            Object o = null;
            System.out.println(o.toString());
        } catch(NullPointerException e) {
            System.out.println(e + "");
        }
        try {
            Integer.parseInt("bla");
        } catch(IllegalArgumentException e) {
            System.out.println(e + "");
        }
        try {
            Integer.parseInt("ble");
        } catch(NumberFormatException e) {
            System.out.println(e + "");
        }
    }
}
