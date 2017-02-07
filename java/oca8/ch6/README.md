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

`Error` and its subclasses are fatal errors.

`Exception` and its subclasses that do not descend from
`RuntimeException` are _checked exceptions_.
Checked exceptions are recoverable errors that are expected during
runtime.

`RuntimeException` and its subclasses are _unchecked exceptions_.
Unchecked exceptions are recoverable errors that are not expected
during runtime.

## Handle or declare rule

The rule is that a given method either handles exceptions raised
during its execution or declares that those exceptions will escape the
method.

It is possible to catch checked and unchecked exceptions.

```java
TODO
```

Only checked exceptions are required to be handled or declared to be
thrown.

```java
TODO
```

Errors may not be caught and may not be declared to be thrown.

```java
TODO
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