# Chapter 6

## Exception types

`Throwable` is the superclass of all exceptions.

```asciidoc
                 java.lang.Object
                        ^
                        |
               java.lang.Throwable
                     ^     ^
                    /       \
    java.lang.Exception   java.lang.Error
           ^
           |
java.lang.RuntimeException
```

### Error

`Error` and its subclasses are fatal errors.

For the exam, think that errors are thrown only by the JVM and should
not be handled by the programmer.

Relevant errors for the exam are the following.

- ExceptionInInitializerError: a static initializer throws an exception
- StackOverflowError: you can't stop recursing
- NoClassDefFoundError: you accidentally deleted a *.class file

### Checked exceptions

`Exception` and its subclasses that do not descend from
`RuntimeException` are _checked exceptions_.
(**Actually, Java docs say that `Throwable` and any subclass that is not
also a subclass of either `Error` or `RuntimeException` is regarded as
a checked exception.**)
Checked exceptions are recoverable errors that are expected during
runtime.

For the exam, think that a given checked exception is either thrown by
a programmer or by the JVM.

Relevant checked exceptions for the exam are the following.

- FileNotFoundException (subclass of IOException)
- IOException

### Unchecked exceptions

`RuntimeException` and its subclasses are _unchecked exceptions_.
(**Java docs say that also `Error` and its subclasses are regarded as
unchecked exceptions.**)
Unchecked exceptions are recoverable errors that are not expected
during runtime.

For the exam, think that a given runtime exception is either thrown by
user code or by the JVM.

Relevant unchecked exceptions for the exam are the following.

1. ArithmeticException
2. ArrayIndexOutOfBoundsException
3. ClassCastException
4. IllegalArgumentException
5. NullPointerException
6. NumberFormatException (subclass of IllegalArgumentException)

## Handle or declare rule

The rule is that a given method either handles exceptions raised
during its execution or declares that those exceptions will escape the
method.

It is possible to catch checked and unchecked exceptions.

```java
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
```

Only checked exceptions are required to be handled or declared to be
thrown.

```java
class WhatMustIHandleOrDeclare {
    public static void checked() {
        throw new CheckedException(); // error: unreported exception CheckedException; must be caught or declared to be thrown
    }
    public static void unchecked() {
        throw new UncheckedException(); // this line causes no compiler error
    }
}
```

Errors may be caught but should not be.
Also, errors are unchecked.

```java
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
```

## Try-Catch-Finally statement

Braces around each block of code are required.

```java
try // error: '{' expected
    System.out.println("w00t");
catch (Exception e) // error: '{' expected
    System.out.println("catch");
```

A statement may omit either the catch clause or the finally clause.

```java
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
```

Contrary to popular belief, the finally clause is not always
executed. Consider the following example that only prints "exiting
now".

```java
try {
  System.out.println("exiting now");
  System.exit(0);
} finally {
  System.out.println("unreachable statement");
}
```

## Order of execution of catch clauses

Catch clauses are evaluated in the order in which they appear.
For example, the following code catches exceptions `TheChild`,
`TheParent`, and `RuntimeException` in that order.

```java
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
    }
}
```

Changing the order of the catch clauses means that some exception type
will be caught by the catch clause of a corresponding superclass. For
that reason, the compiler refuses to compile the following example.

```java
class TheParent extends RuntimeException { }
class The Child extends TheParent { }

class CatchOrder {
  public void exec() {
    try {
      throw new TheParent();
    } catch(TheParent e)
      System.out.println("TheParent");
    } catch(TheChild e) // error: exception TheChild has already been caught
      System.out.println("TheChild");
    }
  }
}
```

## A checked exception must be possible try clause

A try clause must either throw a checked exception or call a method
that throws a checked exception. Otherwise, the code won't
compile. For example:


```java
class IMustBeThrown extends Exception { }

class MustThrowCheckedException {
    public void exec() {
        try {
            System.out.println("I don't throw any exceptions");
        } catch(IMustBeThrown e) { // error: exception IMustBeThrown is never thrown in body of corresponding try statement
            System.out.println("caught IMustBeThrown");
        }
    }
}

```

## Masking exceptions

When a catch clause and a corresponding finally clause each throw an
exception, the exception thrown by the finally clause masks the
exception thrown by the catch clause, like so.

```java
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
```

## Overriding with exceptions

When a method declaration overrides another method, the declaration
may not include any new **checked** exceptions.

```java
class IMustBeThrown extends Exception { }

class ParentClass {
    public void task() throws IMustBeThrown { }
}

class BadChildClass extends ParentClass {
    public void task() throws Exception { } // error: task() in ChildClass cannot override task() in ParentClass, overridden method does not throw Exception
}

class GoodChildClass extends ParentClass {
    public void task() throws RuntimeException { } // this is fine!
    public static void main(String[] args) {
        new GoodChildClass().task(); // this call is fine, runtime exceptions are unchecked exceptions
    }
}
```

## TODO / Study

- ~~Common runtime exceptions~~
- ~~Common checked exceptions~~
- ~~Common errors~~
- ~~Overriding with exceptions~~