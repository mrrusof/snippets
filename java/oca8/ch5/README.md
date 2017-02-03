# Chapter 5

**Objectives**

- Describe inheritance and its benefits (w00t).
- Develop code that demonstrates the use of polymorphism, including
  overriding and object type versus reference type.
  - Understand what polymorphism is.
- Determine when casting is necessary.
- Use super and this to access objects and constructors.
- Use abstract classes and interfaces.

## Inheritance

Inheritance or subclassing is the process by which a child class
automatically includes public and protected members from the
corresponding parent.  Other members are also included but may not be
accessible to the child class.

Java supports single inheritance but classes may implement several interfaces.

Marking a class with specifier final prohibits that the class be subclassed.

## Class declaration

Class declaration consists of the following elements.

- Either public or default access modifier for top-level classes. Only top-level classes are in scope for oca8.
- Optional abstract or final keyword
- Keyword `class`
- Class name
- Optional extends phrase consisting of keyword `extends` and parent class

For example.

```java
public class Car { }
abstract class RaceCar extends Car { }
final class FormulaOneCar extends RaceCar { }
```

## Public access modifier applied to classes

There may be only one public class in a given file and the file name
must be that of the public class.  The corresponding rule applies to
interfaces.

## When you don't indicate a superclass, your class extends java.lang.Object

Consider the following code.

```java
public class Car { }
```

The semantics of that class is the same as the semantics of the
following.

```java
public class Car extends java.lang.Object { }
```

## Use of `super()`

You may only call `super(...)` in the first location of the body of
any given constructor, for example:

```java
class Car {
  private String brand;
  public Car(String brand) {
    this.brand = brand;
  }
}

class RacingCar extends Car {
  public RacingCar() {
    super("Alfa Romeo");
  }
}
```

## Default call to `super()`

If a given constructor does not call any version of `super(...)` or
`this(...)`, Java will call `super()` by default. Consider the
following example that does not compile.

```java
class Car {
  private String brand;
  public Car(String brand) {
    this.brand = brand;
  }
  public String getBrand() {
    return brand;
  }
}

1: class RacingCar extends Car {
2:   public RacingCar() {
3:     // A call to Car() will happen before executing the following line.
4:     // That call breaks the program.
5:     System.out.println("RacingCar() does nothing.");
6:   }
7: }
```

Given that constructor `Car()` is called in line 3 and there is no
such constructor in class `Car`, the code does not compile.  A way to
remove the compile error is to declare constructor `Car()` as follows.

```java
class Car {
  private String brand;
  public Car(String brand) {
    this.brand = brand;
  }
  public Car() {
    this("Opel");
  }
  public String getBrand() {
    return brand;
  }
}
```

Another way is to call a constructor of your choice, for example:

```java
1 : class RacingCar extends Car {
2 :   public RacingCar() {
3 :     super("Alfa Romeo");
5 :   }
6 : }
```

## Constructors are not inherited

For example, the following will not compile because there is no
constructor `RacingCar(String)`.

```java
class Car {
  private String brand;
  public Car(String brand) {
    this.brand = brand;
  }
  public String getBrand() {
    return brand;
  }
}

class RacingCar extends Car { }

class Main {
  public static void main(String[] aa) {
    System.out.println(new RacingCar("Ferrari").getBrand());
  }
}
```

## Access to inherited members

A class that extends another may use public and protected members. For
example:

```java
// file pkg1.Car.java

package pkg1;

public class Car {
  private static int count = 0;
  private String brand;
  private int id;

  public Car(String brand) {
    this.brand = brand;
    id = count++;
  }

  public String getBrand() {
    return brand;
  }

  protected int getId() {
    return id;
  }
}

// file pkg2.RacingCar.java

package pkg2;

import pkg1.Car;

public class RacingCar extends Car {
  public RacingCar(String brand) {
    super(brand);
  }
  public String getRacingNumber() {
    return getBrand() + " " + getId();
  }
}
```

You may access an inherited member via keywords `this` or `super`, for
example:

```java
package pkg2b;

import pkg1.Car;

public class RacingCar extends Car {
  public RacingCar(String brand) {
    super(brand);
  }
  public String getRacingNumber() {
    return this.getBrand() + " " + super.getId();
  }
}
```

## Overriding methods

Given an instance method in a class, you override the method in a
subclass by declaring another method that satisfies the following
conditions.

1. The method must have the same signature (method name and parameter
   type list).
2. The method must be at least as accessible as the overridden method.
3. Each exception must be covariant wrt. some exception thrown by the
   overriden method.
4. The return type must be covariant.
5. The method is an instance method.

Consider the following example for the first two conditions.

```java
// file pkg2c.RacingCar;

import pkg1.Car;

public class RacingCar extends Car {
  public RacingCar(String brand) {
    super(brand);
  }
  public int getId() {
    return super.getId() + 100;
  }
  public String getRacingNumber() {
    return getBrand() + " " + getId();
  }
}
```

The use of `super` in method `RacingCar#getId()` is necessary. Had we
not used `super`, calls to the method would recurse and cause **stack
overflow**.

For the third and fourth conditions, consider the following example
that compiles because each exception in the overriding method is
covariant wrt. `Exception`.

```java
class Car {

    private List<String> drivers;

    public Car(List<String> drivers) {
        this.drivers = drivers;
    }

    public List<String> getDrivers() throws Exception {
        if(drivers.size() == 0)
            throw new Exception("not enough drivers");
        return drivers;
    }
}

class RacingCar extends Car {

    public RacingCar(ArrayList<String> l) {
        super(l);
    }

    public ArrayList<String> getDrivers() throws RuntimeException, Exception {
        try {
            return (ArrayList<String>)super.getDrivers();
        } catch(Exception e) {
            throw new RuntimeException("parent had exception");
        }
    }
}
```

For the third rule, we may remove all exceptions from the overriding
method `getDrivers()` and still get a program that compiles.

```java
class RacingCarNoEx extends Car {

    public RacingCarNoEx(ArrayList<String> l) {
        super(l);
    }

    public ArrayList<String> getDrivers() {
        try {
            return (ArrayList<String>)super.getDrivers();
        } catch(Exception e) {
            return null;
        }
    }

}
```

## Redeclaring private methods

When a subclass declares a method that has the same name as a private
method in a superclass, neither the rules for overriding nor the rules
for overloading apply.  For example, the following program compiles
even if each declaration of `getNumberOfHumps` returns a different
type.

```java
public class Camel {
  private String getNumberOfHumps() {
    return null;
  }
}

public class BactrianCamel extends Camel {
  public int getNumberOfHumps() {
    return 2;
  }
  public BactrianCamel() {
    System.out.println(getNumberOfHumps()); // prints "2"
  }
}
```

## Hiding methods

Given a class method in a class, you hide the method in a subclass by
declaring another method that satisfies the following conditions.

1. The method must have the same signature (method name and parameter
   type list).
2. The method must be at least as accessible as the hidden method.
3. Each exception must be covariant wrt. some exception thrown by the
   hidden method.
4. The return type must be covariant.
5. The method is a class method.

For example:

```java
class Car {
    public static String getClassName() {
        return "Car";
    }
}

class RacingCar extends Car {
    public static String getClassName() {
        return "RacingCar";
    }
    public static void main(String[] aa) {
        System.out.println(RacingCar.getClassName()); // prints "RacingCar"
    }
}
```

You may use `super` in an instance context to call the hidden
method. You may not use non-static variable `super` from a static
context to call the hidden method. For example:

```java
class RacingCar extends Car {
    public static String getClassName() {
        // *** non-static variable super cannot be referenced from a static context
        //return super.getClassName() + " RacingCar";
        return "RacingCar";
    }
    public String toString() {
        return getClassName() + " (parent " + super.getClassName() + ")";
    }
    public static void main(String[] aa) {
        System.out.println(new RacingCar()); // prints "RacingCar (parent Car)"
    }
}
```

## Inherited code calls overriding methods but not hidding methods

In inherited code, a given call to a method that is overridden executes
the overriding method.  In inherited code, a given call to a method
that is hidden executes the hidden method. For example:

```java
class Car {
  public static float getMaxKmH() {
    return 100;
  }
  public String getBrand() {
    return "Volkswagen";
  }
  public String toString() {
    return getBrand() + " w/ max speed of " + getMaxKmH + " km/h";
  }
}

class RacingCar extends Car {
  public static float getMaxKmH() {
    return 350;
  }
  public String getBrand() {
    return "Alfa Romeo";
  }
  public static void main(String[] args) {
    System.out.println(new Car()); // prints "Volkswagen w/ max speed of 100.0 km/h" as expected
    System.out.println(new RacingCar()); // prints "Alfa Romeo w/ max speed of 100.0 km/h"
  }
}
```

## Keyword `final` prevents overriding and hiding

For example:

```java
class Car {
    public static final float getMaxKmH() {
        return 100;
    }
    public final String getBrand() {
        return "Volkswagen";
    }
}

class RacingCar extends Car {
    public static float getMaxKmH() { // compile error, cannot hide getMaxKmH()
        return 350;
    }
    public String getBrand() { // compile error, cannot override getBrand()
        return "Alfa Romeo";
    }
}
```

## Hiding variables

Given an instance or class variable in a class, you hide the variable
by declaring a variable that satisfies the following conditions.

1. The variable has the same name.

In inherited code, access to the hidden variable references the hidden
variable.

**For instance and class variables, there is no feature that corresponds
to method overriding.**

From an instance context, you may use `super` to access a
hidden variable, be it static or instance.

For example:

```java
class Car {
    public static int cylinders = 4;
    private String brand = "Volkswagen";
    public String toString() {
        return "Car " + brand + "-" + cylinders;
    }
}

class RacingCar extends Car {
    protected long cylinders = 6;
    protected static StringBuilder brand = new StringBuilder("Alfa Romeo");

    public String toString() {
        return "RacingCar " + brand + "-" + cylinders + " descendant of " + super.toString();
    }
}

class Formula1Car extends RacingCar {
    private short cylinders = 4;
    public String toString() {
        return "Formula1Car " + brand + "-" + cylinders + " descendant of " + super.brand + "-" + super.cylinders;
    }
    public static void main(String[] args) {
        // prints "Car Volkswagen-4"
        System.out.println(new Car());
        // prints "RacingCar Alfa Romeo-6 descendant of Car Volkswagen-4"
        System.out.println(new RacingCar());
        // prints "Formula1Car Alfa Romeo-4 descendant of Alfa Romeo-6"
        System.out.println(new Formula1Car());
        // *** non-static variable instanceCount cannot be referenced from a static context
        //System.out.println("instanceCount = " + instanceCount);
    }
}
```

## Abstract classes

### Declaration of abstract classes and methods

The declaration of each abstract method ends with a semicolon.

```java
abstract class Worker {

    private String name;

    public Worker(String name) {
        this.name = name;
    }

    protected abstract void task(); // THIS ONE HERE

    public void work() {
        System.out.println(LocalDateTime.now() + " BEGIN " + name);
        task();
        System.out.println(LocalDateTime.now() + " END " + name);
    }
}
```

Remember that keyword `abstract` may precede any access modifier.

```java
abstract class Worker {
...
    abstract protected void task(); // THIS ALSO WORKS
...
}
```

A given abstract method may only be declared in an abstract class.

```java
class Worker {
...
    protected abstract void task(); // DOES NOT COMPILE
...
}
```

A method or class may not be abstract and final.

```java
abstract class Worker {
...
    // following line gives "error: illegal combination of modifiers: abstract and final"
    protected final abstract void task();
...
}
```

A method may not be abstract and private.

```java
abstract class Worker {
...
    // the following line gives "error: illegal combination of modifiers: abstract and private"
    private abstract void task();
...
}
```

An abstract class may not be `protected`. The rule does not apply to
methods.

```java
protected abstract class Worker { } // DOES NOT COMPILE
```

An abstract class may not be `private`.

```java
private abstract class Worker { } // DOES NOT COMPILE
```

The rules that govern abstract classes are the following.

1. Abstract classes cannot be instantiated.
2. Abstract classes may or may not include abstract or nonabstract methods.
3. Abstract classses may not be `private`, `protected`, or `final`.
4. An abstract class that extends another class inherits all of its
   methods. See following sections.
5. Every concrete class must implement all of the inherited abstract
   methods. See following sections.

The rules that govern abstract methods are the following.

1. Abstract methods may only be defined in abstract classes.
2. Abstract methods may not be `private` or `final`.
3. Abstract methods must not provide an implementation.
4. The first 4 rules for overriding methods apply to the
   implementation of abstract methods.

### Implementation of abstract methods

The first 4 rules for overriding methods apply to the implementation
of abstract methods. Consider the following abstract class.

```java
abstract class Printer {
    abstract protected String document() throws TimeoutException;

    public void print() {
        try {
            System.out.println("printed document: " + document());
        } catch(TimeoutException e) {
            System.out.println("the document took too long to render");
        }
    }
}
```

1. The method must have the same signature (method name and parameter
   type list). For example, the following declaration does not override
   abstract method `document` and thus fails to compile.

   ```java
   class NumberPrinter extends Printer {
     protected String document(int n) {
       return n + "";
     }
   }
   ```

   The following declaration works fine.

   ```java
   class NumberPrinter extends Printer {
     protected String document() {
       return "1";
     }
   }
   ```

2. The method must be at least as accessible as the abstract
   method. For example, the following declaration attempts to
   assign weaker access privileges and thus fails to compile.

   ```java
   class NumberPrinter extends Printer {
     private String document() {
         return "1";
     }
   }
   ```

   The following declaration works fine.

   ```java
   class NumberPrinter extends Printer {
     public String document() {
         return "1";
     }
   }
   ```

3. Each exception must be covariant wrt. some exception thrown
   by the abstract method. For example, the following method
   fails to compile because `TimeoutException` is not a superclass of
   `Exception`.

   ```java
   class NumberPrinter extends Printer {
     protected String document() throws Exception {
         throw new Exception("w00t");
     }
   }
   ```

   The following declarations work fine.

   ```java
   class MyTimeoutException extends TimeoutException {}

   class NumberPrinter extends Printer {
     protected String document() throws TimeoutException, MyTimeoutException {
       throw new TimeoutException();
     }
   }
   ```

4. The return type must be covariant. For example, the
   following method fails to compile because return type
   StringBuilder is not a subtype of String.

   ```java
   class NumberPrinter extends Printer {
     protected StringBuilder document() {
         return new StringBuilder("1");
     }
   }
   ```

### Concrete class

A concrete class is a nonabstract class that extends an abstract
class. A given concrete class must implement all abstract methods
inherited. For example, class `NumberPrinter` is a concrete class.

```java
abstract class Printer {
  protected abstract String document();

  public void print() {
    System.out.println("printed document: " + document());
  }
}

abstract class TimestampedPrinter extends Printer {
  public void print() {
    System.out.println("current date and time: " + LocalDateTime.now());
    super.print();
  }
}

class NumberPrinter extends TimestampedPrinter { // CONCRETE CLASS
  protected String n;
  public NumberPrinter(int n) {
    this.n = n + "";
  }
  protected String document() {
    return n;
  }
}
```

### An abstract class may extend another abstract class

The example in the previous section shows this case.

### An abstract class may even extend a nonabstract class

For example:

```java
class BlankPrinter {
  protected String document() {
    return "";
  }

  public void print() {
    System.out.println("printed document: " + document());
  }
}

abstract class HeaderPrinter extends BlankPrinter {
  protected abstract String header();
  public void print() {
    System.out.println("header of document: " + header());
    super.print();
  }
}

class HelloPrinter extends HeaderPrinter {
  protected String header() {
    return LocalDateTime.now() + "";
  }
  protected String document() {
    return "hello";
  }
}
```

### Can an abstract class prescribe fields?

TODO

## Interfaces

```java
   public abstract interface Printer {
// ------ --------
//   |       |
// public  implicit
//   or
// default

   public static final String DEFAULT_PAPER_SIZE = "A4";
// -------------------
//          |
//       implicit

   public abstract void print();
// ---------------
//        |
//     implicit

}
```

The implicit parts are given by Java when you don't write them.
For example, the implementation of method `print()` in the following
code must be public so that the code compiles.

```java
interace Printer {
  // private String DEFAULT_PAPER_SIZE = "A4"; // FAILS WITH "error: modifier private not allowed here"
  String DEFAULT_PAPER_SIZE = "A4"
  // private void print(); // FAILS WITH "error: modifier private not allowed here"
  void print();
}

class NumberPrinter implements Printer {

  // void print() { // FAILS WITH "attempting to assign weaker access privileges; was public"

  public void print() {
    System.out.println("paper_size: " + DEFAULT_PAPER_SIZE);
    for(int i = 0; i < 10; i++)
      System.out.println(i);
  }
}
```

No member of an interface may be private, as illustrated in the
previous example.

### Rules

The rules that govern interfaces are the following.

TODO: review these rules.

1. Interfaces cannot be instatiated directly.

2. An interface is not required to have any methods.

3. An interface may not be marked as final.

4. Each top-level interface is either `public` or
   has default access.

5. An interface is `abstract` regardless of whether you apply the
   keyword `abstract` or not.

6. Methods may be abstract, default or static.
  1. Abstract methods are `public` and `abstract` and
     therefore may not be `private`, `protected`,
     `final`, or `static`.
  2. Default methods are public ... TODO
  3. Static methods are `public` ... TODO

7. A field is considered `public`, `final`, and `static`.

8. A field must be set when declared.

Example.

```java
//interface Printer { // THIS AND THE FOLLOWING EVALUATE THE SAME
abstract interface Printer {
    // private String DEFAULT_PAPER_SIZE = "A4"; // FAILS WITH "error: modifier private not allowed here"

    // public String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    // static String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    // final String DEFAULT_PAPER_SIZE = "A4"; // THIS AND THE FOLLOWING EVALUATE THE SAME
    String DEFAULT_PAPER_SIZE = "A4";

    // private void print(); // FAILS WITH "error: modifier private not allowed here"
    // final void print(); // FAILS WITH "error: modifier final not allowed here"
    // static abstract void print(); // FAILS WITH "illegal combination of modifiers: abstract and static"

    // public abstract void print(); // THE FOLLOWING EVALUATE THE SAME
    // public void print(); // THIS AND THE FOLLOWING EVALUATE THE SAME
    // abstract void print(); // THIS AND THE FOLLOWING EVALUATE THE SAME
    void print();
}
```

## An interface may extend another

The child inherits all abstract methods and any implementing class
must implement all.

```java
interface Renderable {
  String render();
}

interface Printable extends Renderable {
  void print();
}

class PrintablePrinter implements Printable {
  public String render() { return "document"; }
  public void print() { System.out.println(render()); }
}
```

## An abstract class may implement an interface

```java
interface Renderable {
  String render();
}

abstract class Printer implements Renderable {
  void print() { System.out.println(render()); }
}

class HelloPrinter extends Printer {
  public String render() { return "hello"; }
}
```

## Repeat: rules for interface variables

The last two rules for interfaces govern interface fields and they are
the following.

1. Every field is considered `public`, `static`, and `final`.
2. Every field must be set when declared.

Rule 1 means that you cannot make a field `private`, `protected` or
`abstract`.

```java
interface Document {
    private int DEFAULT_PAGE_COUNT = 1; // private conflicts with public
    protected String DEFAULT_PAGE_SIZE = "A4"; // protected conflicts with public
    abstract String DEFAULT_BODY = "hello"; // abstract conflicts with final
}
```

Rule 1 also means that the following two interfaces have the same
semantics.

```java
interface Document {
  int DEFAULT_PAGE_COUNT = 1;
}

interface Document {
  public static final int DEFAULT_PAGE_COUNT = 1;
}
```


## Default interface methods (introduced in Java 8)

The purpose of a default method is to provide a default implementation
that can be overriden. Thus, default methods cannot be static, final
or abstract.

With default methods you can write something very similar to abstract
class from "Implementation of abstract methods". Difference is that
you cannot make `document()` protected.

```java
interface Printer {
  String document() throws TimeoutException;
  default void print() {
    try {
      System.out.println("printed document: " + document());
    } catch(TimeoutException e) {
      System.out.println("the document took too long to render");
    }
  }
}

class NumberPrinter implements Printer {
    public String document() {
        return "1";
    }
    public static void main(String[] args) {
        new NumberPrinter().print(); // "printed document: 1"
    }
}
```

### Rules

1. A default method may only be declared in an interface.
2. A default method indicates keyword `default`.
3. A default method provides a body.
4. A default method will not compile if made static, final, or
   abstract.
   TODO: example
5. A default method is public and will not compile if marked
   private or protected.
   TODO: example (p.275)
6. An interface that extends another may override a default method
   following the rules for overriding.
   TODO: example (p.275)
7. An interface that extends another may hide a default method by
   redeclaring it as abstract.
   TODO: example (p.275)
8. Rules 6 and 7 apply to an abstract class that implements an
   interface.
   TODO: example

For rule 4, consider the following example.

```java
interface Printer8 {
    // *** illegal combination of modifiers: static and default
    static default void print() {
        System.out.println("hello");
    }

    // *** modifier final not allowed here
    final default void print() {
        System.out.println("hello");
    }

    // *** illegal combination of modifiers: abstract and default
    abstract default void print() {
        System.out.println("hello");
    }
}
```

For rule 5, consider the following example.

```java
interface Printer9 {
    // *** modifier private not allowed here
    private default void print() {
        System.out.println("hello");
    }

    // *** modifier protected not allowed here
    protected default void print() {
        System.out.println("hello");
    }
}
```

## Recap: Rules 6, 7, 8

An interface that extends another that defines a default method may
take one of three actions.

1. Inherit the default method.
2. Override the default method with another default declaration (rule 6)
3. Hide the default method with an abstract declaration (rule 7)

TODO: rule 8

## Static interface methods (Java 8)

**Main diff compared to regular static methods.** A static interface
method is not inherited by any class that implements the interface.

Static interface methods satisfy the following rules.

1. Each static interface method is public and will not compile if
   marked `private` or `protected`.

   ```java
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
   ```

2. To reference a given static interface method, use the name of the
   corresponding interface.

   ```java
   class NumberPrinter implements Printer {
       public static void main(String[] args) {
           System.out.println("Printer.pageCount() = " + Printer.pageCount()); // "Printer.pageCount() = 1"
           System.out.println("NumberPrinter.pageCount() = " + NumberPrinter.pageCount()); // cannot find symbol
           System.out.println("pageCount() = " + pageCount()); // cannot find symbol
       }
   }
   ```

   The code fails to compile because class NumberPrinter does not
   inherit static interface method `pageCount()`.


## Multiple inheritance

### Two interfaces with same abstract method

There are two rules.

1. A class may implement two interfaces that prescribe the same
   abstract method, for example:

   ```java
   interface Document {
     void print();
   }

   interface Printer {
     void print();
   }

   class PrintNumber implements Document, Printer {
     public void print() {
       System.out.println(1);
     }
   }
   ```

2. However, one class may not implement two interfaces that prescribe
   methods with the same signature and different return type.

   ```java
   interface Document {
     String print();
   }

   interface Printer {
     void print();
   }

   class PrintNumber implements Document, Printer { // COMPILE ERROR "PrintNumber is not abstract and does not override abstract method print() in Printer"
     public String print() { // COMPILE ERROR HERE
       return "1";
     }

     public void print() { // COMPILE ERROR HERE
       System.out.println(1);
     }
   }
   ```

   The same goes for abstract classes and interfaces.

   ```java
   // ERROR "types Printer and Document are incompatible; both define print(), but with unrelated return types"
   abstract class PrintStuff implements Document, Printer { }

   // ERROR "types Printer and Document are incompatible; both define print(), but with unrelated return types"
   interface IPrintStuff extends Document, Printer { }
   ```

### Two interfaces with same default method

There are two rules.

1. When two interfaces define two default methods of the same signature,
   a class that implements the interfaces may not inherit both methods.
   For example:

   ```java
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

   // class PrintableDocument inherits unrelated defaults for print() from types Document and Printer
   class PrintableDocument implements Document, Printer { }
   ```

   The rule applies to abstract classes and interfaces as well.

   ```java
   // class PrintableDocument inherits unrelated defaults for print() from types Document and Printer
   abstract class PrintableDocument implements Document, Printer { }

   // interface PrintableDocument inherits unrelated defaults for print() from types Document and Printer
   interface PrintableDocument extends Document, Printer { }
   ```

2. When two interfaces define two default methods of the same signature,
   a class that implements the interfaces may override both methods, for
   example:

   ```java
   class PrintableDocument implements Document, Printer {
       public void print() {
           System.out.println("printable document");
       }
       public static void main(String[] args) {
           new PrintableDocument().print(); // "printable document"
       }
   }
   ```

   The rule applies to abstract classes and interfaces as well.

   ```java
   abstract class PrintableDocument implements Document, Printer {
       public void print() {
           System.out.println("printable document");
       }
   }

   interface PrintableDocument extends Document, Printer {
     default void print() {
       System.out.println("printable document");
     }
   }
   ```

### Two interfaces with same static method

Two interfaces that define the same static method may be implemented
by a single class.
For example:

```java
interface Document {
  static void print() {
    System.out.println("document");
  }
}

interface Printer {
  static void print() {
    System.out.println("printer");
  }
}

class DocumentPrinter implements Document, Printer { }
```

The same applies to interfaces and abstract classes.

```java
interface IDocumentPrinter extends Document, Printer { }

abstract class ADocumentPrinter implements Document, Printer { }
```

### Two interfaces with same field

TODO: explain

## Polymorphism

TODO: defn

You may reference an instance of a class by means of a reference that
satisfies one of the following conditions.

1. The type of the reference is the class.

   ```java
   class Car {
     public static void main(String[] args) {
       Car c = new Car;
     }
   }
   ```

2. The type of the reference is a superclass.

   ```java
   class Car {}
   class RacingCar extends Car {
     public static void main(String[] args) {
       Car c = new RacingCar();
     }
   }
   ```

3. The type of the reference is an interface that the class implements.

   ```java
   interface Printable { }
   class Document implements Printable {
     public static void main(String[] args) {
       Printable p = new Document();
     }
   }
   ```

The type of an object determines the members of the object.
The type of a reference to the object determines the methods and
variables accessible.

## Casting

1. Casting a reference from a subclass to a superclass does not require
   explicit cast.
2. Casting a reference from a superclass to a subclass requires an
   explicit cast.
3. (inane) Is not possible to cast unrelated types.

Casting works as long as the object involved is a subtype of the
target type.

## Virtual methods

TODO

## The mandatory polymorphic parameter passing example

TODO

## Study / review

- The 5 conditions for overriding
- The 5 conditions for hiding
- What method is called when method is overriden / hidden.
- What variable is accessed when variable is hidden.
- The 7 rules that govern interfaces
- Multiple inheritance
- Default interface methods
- Static interface methods
- Kinds of methods you can have in an interface.
