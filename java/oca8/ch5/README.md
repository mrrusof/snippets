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

### Intro to abstract classes

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

An abstract class may not be `final`.

```java
final abstract class Worker { } // DOES NOT COMPILE
```

### Rules for abstract classes and their members

The rules that govern abstract classes are the following.

1. Abstract classes cannot be instantiated.
2. Abstract classes may have an empty body.
3. Abstract classes may include fields.
4. Abstract classes may include the following kinds of methods.
   1. Abstract methods
   2. Regular methods
5. Abstract classes may not be `private`, `protected` or `final`.
6. An abstract class that extends another class inherits its
   members. See following sections.
7. Every concrete class must implement all of the inherited abstract
   methods. See following sections.

The rules that govern abstract methods are the following.

1. Abstract methods may only be defined in abstract classes.
2. Abstract methods may not be `private`, `final` or `static`.
3. Abstract methods must not provide an implementation.
4. The first 4 rules for overriding methods apply to the
   implementation of abstract methods.

The rules that govern regular methods in abstract classes are the
following.

1. There are no restrictions on access modifiers for regular methods
   (instance or static, final or not).
2. Regular methods are accessible and inherited like fields of non
   abstract classes.
3. The rules for overriding/hiding apply to regular methods that are
   instance/static.
4. Instance regular methods may be hidden by declaring corresponding
   abstract methods.

The rules that govern fields in abstract classes are the following.

1. There are no restrictions on access modifiers for fields.
2. Fields are accessible and inherited like fields of non abstract
   classes.
2. Fields given by an abstract class may be hidden following the rule
   for hiding. See section "Hiding variables."

### Declaration of abstract methods

Abstract methods may not be `private`, `final` or `static`.

```java
abstract class MultiTaskWorker {
    // error: illegal combination of modifiers: abstract and private
    //private abstract void task();

    // error: illegal combination of modifiers: abstract and final
    //final abstract void task();

    // error: illegal combination of modifiers: abstract and static
    //static abstract void task();

              abstract void task1();
    protected abstract void task2();
    public    abstract void task3();
}
```

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

### Declaration, access, inheriting, and overriding of regular methods in abstract classes

Nonabstract methods (instance or static, final or not) declared in
abstract classes are accessible, inherited, and hidden/overridden like
methods in nonabstract classes.

```java
abstract class MultiTaskWorker2 {
    static private   void sTask1() { System.out.println("MultiTaskWorker2.sTask1"); }
    static           void sTask2() { System.out.println("MultiTaskWorker2.sTask2"); }
    static protected void sTask3() { System.out.println("MultiTaskWorker2.sTask3"); }
    static public    void sTask4() { System.out.println("MultiTaskWorker2.sTask4"); }

    static final private   void sfTask1() { System.out.println("MultiTaskWorker2.sTask1"); }
    static final           void sfTask2() { System.out.println("MultiTaskWorker2.sTask2"); }
    static final protected void sfTask3() { System.out.println("MultiTaskWorker2.sTask3"); }
    static final public    void sfTask4() { System.out.println("MultiTaskWorker2.sTask4"); }

    private   void task1() { System.out.println("MultiTaskWorker2.task1"); }
              void task2() { System.out.println("MultiTaskWorker2.task2"); }
    protected void task3() { System.out.println("MultiTaskWorker2.task3"); }
    public    void task4() { System.out.println("MultiTaskWorker2.task4"); }

    final private   void fTask1() { System.out.println("MultiTaskWorker2.task1"); }
    final           void fTask2() { System.out.println("MultiTaskWorker2.task2"); }
    final protected void fTask3() { System.out.println("MultiTaskWorker2.task3"); }
    final public    void fTask4() { System.out.println("MultiTaskWorker2.task4"); }
}

class InheritingWorker extends MultiTaskWorker2 { }

class OverridingWorker extends MultiTaskWorker2 {
    private   void task1() { System.out.println("OverridingWorker.task1"); } // method redeclared, not overridden
              void task2() { System.out.println("OverridingWorker.task2"); }
    protected void task3() { System.out.println("OverridingWorker.task3"); }
    public    void task4() { System.out.println("OverridingWorker.task4"); }

    final private   void fTask1() { System.out.println("MultiTaskWorker2.task1"); } // method redeclared, not overridden
    //final           void fTask2() { System.out.println("MultiTaskWorker2.task2"); } // error: fTask2() in OverridingWorker cannot override fTask2() in MultiTaskWorker2
    //final protected void fTask3() { System.out.println("MultiTaskWorker2.task3"); } // error: fTask3() in OverridingWorker cannot override fTask3() in MultiTaskWorker2
    //final public    void fTask4() { System.out.println("MultiTaskWorker2.task4"); } // error: fTask4() in OverridingWorker cannot override fTask4() in MultiTaskWorker2
}

class HidingWorker extends MultiTaskWorker2 {
    static private   void sTask1() { System.out.println("HidingWorker.sTask1"); } // method redeclared, not overridden
    static           void sTask2() { System.out.println("HidingWorker.sTask2"); }
    static protected void sTask3() { System.out.println("HidingWorker.sTask3"); }
    static public    void sTask4() { System.out.println("HidingWorker.sTask4"); }

    static final private   void sfTask1() { System.out.println("HidingWorker.sTask1"); } // method redeclared, not overridden
    //static final           void sfTask2() { System.out.println("HidingWorker.sTask2"); } // error: sfTask2() in HidingWorker cannot override sfTask2() in MultiTaskWorker2
    //static final protected void sfTask3() { System.out.println("HidingWorker.sTask3"); } // error: sfTask3() in HidingWorker cannot override sfTask3() in MultiTaskWorker2
    //static final public    void sfTask4() { System.out.println("HidingWorker.sTask4"); } // error: sfTask4() in HidingWorker cannot override sfTask4() in MultiTaskWorker2

              void superSTask2() { super.sTask2(); }
    protected void superSTask3() { super.sTask3(); }
    public    void superSTask4() { super.sTask4(); }
}
```

In addition to overriding/hiding like methods in nonabstract classes,
instance regular methods may be hidden by declaring corresponding
abstract methods.

```java
abstract class HidingWorkerByAbstract extends MultiTaskWorker2 {

    // YOU CANNOT HIDE A STATIC METHOD BY ABSTRACT METHOD
    //abstract static void sTask2(); // error: illegal combination of modifiers: abstract and static
    //abstract        void sTask2(); // error: sTask2() in HidingWorkerByAbstract cannot override sTask2() in MultiTaskWorker2, overridden method is static

    // YOU CANNOT HIDE A FINAL METHOD EITHER
    abstract           void fTask2(); // error: fTask2() in HidingWorkerByAbstract cannot override fTask2() in MultiTaskWorker2, overridden method is final

    // INSTANCE METHODS ARE OK
    abstract           void task2();
    abstract protected void task3();
    abstract public    void task4();
}
```

### Declaration, access, inheriting, and hiding of fields

Fields declared in an abstract classes are accessible, inherited, and
hidden like fields in nonabstract classes.

For example, methods `print()` and `printInherited()` of
`InheritFields` print the fields declared in abstract class
`Fields`. Method `printHiddenFields()` also prints the fields declared
in `Fields`. Method `printFieldsThatHide()` prints the fields defined
in class `HideFields`.

```java
abstract class Fields {
    private   String pri = "Fields: private field";
              String d   = "Fields: package default field";
    protected String pro = "Fields: protected field";
    public    String pu  = "Fields: public field";

    final private   String fpri = "Fields: final private field";
    final           String fd   = "Fields: final package default field";
    final protected String fpro = "Fields: final protected field";
    final public    String fpu  = "Fields: final public field";

    static private String spri = "Fields: static private field";
    static         String sd   = "Fields: static package default field";
    static         String spro = "Fields: static protected field";
    static         String spu  = "Fields: static public field";

    final static private String fspri = "Fields: final static private field";
    final static         String fsd   = "Fields: final static package default field";
    final static         String fspro = "Fields: final static protected field";
    final static         String fspu  = "Fields: final static public field";

    public void print() {
        System.out.println("Fields: " + pri);
        System.out.println("Fields: " + d);
        System.out.println("Fields: " + pro);
        System.out.println("Fields: " + pu);

        System.out.println("Fields: " + fpri);
        System.out.println("Fields: " + fd);
        System.out.println("Fields: " + fpro);
        System.out.println("Fields: " + fpu);

        System.out.println("Fields: " + spri);
        System.out.println("Fields: " + sd);
        System.out.println("Fields: " + spro);
        System.out.println("Fields: " + spu);

        System.out.println("Fields: " + fspri);
        System.out.println("Fields: " + fsd);
        System.out.println("Fields: " + fspro);
        System.out.println("Fields: " + fspu);
    }
}

class InheritFields extends Fields {
    public void printInherited() {
        //System.out.println("InheritFields: " + pri); // error: pri has private access in Fields
        System.out.println("InheritFields: " + d);
        System.out.println("InheritFields: " + pro);
        System.out.println("InheritFields: " + pu);

        //System.out.println("InheritFields: " + fpri); // error: fpri has private access in Fields
        System.out.println("InheritFields: " + fd);
        System.out.println("InheritFields: " + fpro);
        System.out.println("InheritFields: " + fpu);

        //System.out.println("InheritFields: " + spri); // error: spri has private access in Fields
        System.out.println("InheritFields: " + sd);
        System.out.println("InheritFields: " + spro);
        System.out.println("InheritFields: " + spu);

        //System.out.println("InheritFields: " + fspri); // error: fspri has private access in Fields
        System.out.println("InheritFields: " + fsd);
        System.out.println("InheritFields: " + fspro);
        System.out.println("InheritFields: " + fspu);
    }
}

class HideFields extends Fields {

              String d   = "HideFields: package default field";
    protected String pro = "HideFields: protected field";
    public    String pu  = "HideFields: public field";

    final           String fd   = "HideFields: final package default field";
    final protected String fpro = "HideFields: final protected field";
    final public    String fpu  = "HideFields: final public field";

    static         String sd   = "HideFields: static package default field";
    static         String spro = "HideFields: static protected field";
    static         String spu  = "HideFields: static public field";

    final static         String fsd   = "HideFields: final static package default field";
    final static         String fspro = "HideFields: final static protected field";
    final static         String fspu  = "HideFields: final static public field";

    public void printFieldsThatHide() {
        System.out.println("HideFields: " + d);
        System.out.println("HideFields: " + pro);
        System.out.println("HideFields: " + pu);

        System.out.println("HideFields: " + fd);
        System.out.println("HideFields: " + fpro);
        System.out.println("HideFields: " + fpu);

        System.out.println("HideFields: " + sd);
        System.out.println("HideFields: " + spro);
        System.out.println("HideFields: " + spu);

        System.out.println("HideFields: " + fsd);
        System.out.println("HideFields: " + fspro);
        System.out.println("HideFields: " + fspu);
    }
    public void printHiddenFields() {
        System.out.println("HideFields: " + super.d);
        System.out.println("HideFields: " + super.pro);
        System.out.println("HideFields: " + super.pu);

        System.out.println("HideFields: " + super.fd);
        System.out.println("HideFields: " + super.fpro);
        System.out.println("HideFields: " + super.fpu);

        System.out.println("HideFields: " + super.sd);
        System.out.println("HideFields: " + super.spro);
        System.out.println("HideFields: " + super.spu);

        System.out.println("HideFields: " + super.fsd);
        System.out.println("HideFields: " + super.fspro);
        System.out.println("HideFields: " + super.fspu);
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
  protected abstract LocalDateTime getDateTime();
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
  protected String document() { // THIS HAD TO BE IMPLEMENTED
    return n;
  }
  public LocalDateTime getDateTime() { // THIS HAD TO BE IMPLEMENTED
    return LocalDateTime.now();
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

## Interfaces

### Declaration of interfaces and its fields and methods

The following code illustrates the syntax of interfaces.

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
interface Printer {
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

### Rules for interfaces and their members

We are only concerned with top-level interfaces.

The rules that govern top-level interfaces are the following.

1. Interfaces cannot be instantiated.
2. Interfaces may have an empty body
3. Interfaces may include fields.
4. Interfaces may include the following kinds of methods.
   1. Abstract methods
   2. Default methods
   3. Static methods
5. Interfaces are `abstract` regardless of whether you apply the
   keyword `abstract` or not.
6. Rules 5-7 of abstract classes apply.
   1. Interfaces may not be `private`, `protected` or `final`.
   2. An interface that extends another inherits its members.
   3. Every concrete class must implement all of the inherited
      abstract methods.

The rules that govern abstract methods are the following.

1. Abstract methods are methods that are not marked `default` or
   `static`.
2. Abstract methods are `abstract` and `public` regardless of whether
   you apply the keywords or not. Therefore, abstract methods may not
   be `private`, `final`, `static`, `protected` or package default.
3. Abstract methods must not provide an implementation.
4. The first 4 rules for overriding methods apply to the
   implementation of abstract methods.

The rules that govern default methods are the following.

1. Default methods may only be declared in interfaces.
2. Default methods are methods that are marked `default`. Therefore,
   default methods may not be `final`, `abstract` or `static`.
3. Default methods are `public`. Therefore, default methods may not be
   `private`, `protected` or default package.
4. Default methods must provide an implementation.
5. An interface that extends another that declares a default method
   may take one of three actions.
   1. Inherit the default method.
   2. Override the default method with another default declaration.
      When overriding, overrided method is not accesible by `super`.
      (TODO: compare example p.275)
   3. Hide the default method with an abstact declaration.
      (TODO: compare example p.275)
6. The previous rule applies to an abstract class that implements an
   interface.

The rules that govern static methods are the following.

1. Static methods are marked `static` (duh).
2. Static methods are `public` by default.
3. Static methods are accessible via the name of the interface.
4. Static methods are not inherited.

The rules that govern fields in interfaces are the following.

1. A field is `public`, `final`, and `static` regardless of whether
   you apply the keywords or not.
2. A field must be set when declared.
3. Fields are inherited like fields of non abstract classes.
4. Fields given by an interface may be hidden following the rule for
   hidding. See section "Hiding variables."
5. Fields are not accessible by reference `super`.

### Declaration of interfaces

Interfaces may have an empty body.

```java
interface Article { }
```

Interfaces may include fields.

```java
interface Article {
  String c = "hola";
}
```

Interfaces may include abstract, default, and/or static methods.

```java
interface Article3 {
    void a();
    default void d() {
        System.out.println("Article3: default method");
    }
    static void s() {
        System.out.println("Article3: static method");
    }
}
```

Interfaces are `abstract`.

```java
//interface Printer { } // THIS AND THE FOLLOWING EVALUATE THE SAME
abstract interface Printer { }
```

Interfaces may not be `private`.

```java
// error: modifier private not allowed here
private interface Article4 { }
```

Interfaces may not be `protected`.

```java
// error: modifier protected not allowed here
protected interface Article5 { }
```

Interfaces may not be `final`.

```java
// error: illegal combination of modifiers: interface and final
final interface Article6 { }
```

Interfaces may be `public` or default package.

```java
// file Article2.java
package pkg;

public interface Article2 { ... }

interface Article7 { }

// some other file in other package...

import pkg.Article7; // error: Article7 is not public in pkg; cannot be accessed from outside package
```

### Declaration, access, inheriting, and implementation of abstract methods

Abstract methods are not marked `default` or `static`. Abstract
methods may not be `private`, `final`, `static`,
`protected` or package default. Abstract methods must not provide an
implementation.

```java
interface Printer1 {
                      void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //       abstract void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //public abstract void print(); // THIS AND THE NEXT EVALUATE THE SAME
    //public          void print();

    //private         void print(); // error: modifier private not allowed here
    //final           void print(); // error: modifier final not allowed here
    //static          void print(); // error: missing method body, or declare abstract
    //protected       void print(); // error: modifier protected not allowed here

    // Cannot declare package default method because it will be applied `public abstract` modifiers.
}
```

An interface that implements another inhterits its members.

```java
interface InheritPrinter1 extends Printer1 {
    // inherits `public abstract void print();`
    Object getContents() throws Exception;
}
```

Every concrete class must implement all of the intherited abstract
methods. The first 4 rules for overriding methods apply to the
implementation of abstract methods.

```java
class ImplementPrinter1 implements InheritPrinter1 {
    public void print() {
        System.out.println("ImplementPrinter1: 1");
    }
    public String getContents() { // covariant return and exception list
        return "ImplementPrinter1: 2";
    }
}
```

### Default interface methods (introduced in Java 8) vs. regular instance methods

The purpose of a default method is to provide a default implementation
that can be overridden. Thus, default methods cannot be `static`,
`final` or `abstract`.

Default methods correspond to regular instance methods in abstract
classes. The difference is that a default method, like `print()` in the
following example, cannot be `final` `private`, `protected` or package
default.

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

### Declaration, access, inheriting, and implementation of default methods

Default methods are marked `default` and are always
`public`. Therefore, default methods may not be `abstract`, `static`,
`final`, `private`, `protected` or package default.
Default methods must provide an implementation.

```java
interface Printer4 {
      public    default void pu()  { System.out.println("Printer4: default public method"); } // THIS AND THE NEXT EVALUATE THE SAME
    //          default void d()   { System.out.println("Printer4: default package default method"); }

    //abstract  default void abs() { System.out.println("Printer4: default abstract method"); } // error: illegal combination of modifiers: abstract and default
    //static    default void st()  { System.out.println("Printer4: default static method"); } // error: illegal combination of modifiers: static and default
    //final     default void fin() { System.out.println("Printer4: default final method"); } // error: modifier final not allowed here

    //private   default void pri() { System.out.println("Printer4: default private method"); } // error: modifier private not allowed here
    //protected default void pro() { System.out.println("Printer4: default protected method"); } // error: modifier protected not allowed here

    // Cannot declare package default method because it will be applied modifier `public`.
}
```

An interface that extends another that declares a default method may
either intherit the method, override it or hide it.

```java
interface InheritPrinter4 extends Printer4 {
    // inherits `public default void pu() { ... }`
}

interface OverridePrinter4 extends Printer4 {
    public default void pu()  { // Overriding is really hiding
        //super.pu(); // error: cannot find symbol `super`
        System.out.println("OverridePrinter4: default public method");
    }
}

class ConcreteOverridePrinter4 implements OverridePrinter4 {
    public static void main(String[] args) {
        new ConcreteOverridePrinter4().pu(); // prints "OverridePrinter4: default public method"
    }
}

interface HidePrinter4 extends Printer4 {
    abstract void pu();
}

class ConcreteHidePrinter4 implements HidePrinter4 {
    public void pu() { System.out.println("ConcreteHidePrinter4: public method"); }
    public static void main(String[] args) {
        new ConcreteHidePrinter4().pu(); // prints "ConcreteHidePrinter4: public method"
    }
}
```

The same rule applies to abstract classes that implement interfaces.

```java
abstract class AInheritPrinter4 implements Printer4 {
    // inherits `public default void pu() { ... }`
}

abstract class AOverridePrinter4 implements Printer4 {
    public void pu()  { // Overriding is really hiding
        //super.pu(); // error: cannot find symbol `super`, SAME AS IN EXAMPLE WITH CLASSES
        System.out.println("OverridePrinter4: default public method");
    }
}

class ConcreteAOverridePrinter4 extends AOverridePrinter4 {
    public static void main(String[] args) {
        new ConcreteAOverridePrinter4().pu(); // prints "OverridePrinter4: default public method"
    }
}

abstract class AHidePrinter4 implements Printer4 {
    public abstract void pu(); // remember to indicate access modifier `public`
}

class ConcreteAHidePrinter4 extends AHidePrinter4 {
    public void pu() { System.out.println("ConcreteAHidePrinter4: public method"); }
    public static void main(String[] args) {
        new ConcreteAHidePrinter4().pu(); // prints "ConcreteAHidePrinter4: public method"
    }
}
```

### Static interface methods (Java 8)

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

3. Static methods are not inherited. For example,
   The code fails to compile because class NumberPrinter does not
   inherit static interface method `pageCount()`.


### Declaration, access, inheritance, and hiding of fields

Fields in interfaces are always public constants (`public`, `final`,
and `static`). Therefore, fields may not be `private`, `protected` or
package default. Consider the following example.

```java
package pkg1;

public interface Article2 {
    // error: modifier private not allowed here
    //private   String pri = "Article2: private field";
              String d   = "Article2: static final public field (not instance package default)";
    // error: modifier protected not allowed here
    //protected String pro = "Article2: protected field";
    public    String pu  = "Article2: static final public field (not instance public)";

    // error: modifier private not allowed here
    //static private   String spri = "Article2: static private field";
    static           String sd   = "Article2: static final public field (not static package default)";
    // error: modifier protected not allowed here
    //static protected String spro = "Article2: static protected field";
    static public    String spu  = "Article2: static final public field (not just static public)";

    // error: modifier private not allowed here
    //static final private   String sfpri = "Article2: static final private field";
    static final           String sfd   = "Article2: static final public field (not static final package default) ";
    // error: modifier protected not allowed here
    //static final protected String sfpro = "Article2: static final protected field";
    static final public    String sfpu  = "Article2: static final public field";

    // error: = expected
    // String c; // FIELD MUST BE SET WHEN DECLARED
    // static { c = "assignment won't compile"; }
}
```

Every field must be set when declared. In the previous example, the
declaration and initialization of `c` does not compile when
uncommented.

A field declared in an interface is inherited and hidden like fields of non abstract classes.
A field declared in an interface is not accessible by reference `super`.

```java
class InheritArticle2 implements Article2 {
    static void print() {
        System.out.println("InheritArticle2: " + sfpu); // PRINTS INHERITED FIELD
    }
}

class HideArticle2 implements Article2 {
    static StringBuilder sfpu = new StringBuilder("HideArticle2: static final public field"); // HIDES sfpu
    public void printFieldsThatHide() {
        System.out.println("HideArticle2: " + sfpu); // PRINTS FIELD THAT HIDES sfpu
    }
    public void printHiddenFields() {
        System.out.println("HideArticle2: " + super.sfpu); // error: cannot find symbol sfpu
    }
}
```

### An interface may extend another

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

### An abstract class may implement an interface

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

A class may implement two interfaces that define the same field
name, but they may not reference the field.

```java
interface IHaveField {
    String        field = "IHaveField";
}

interface IHaveFieldToo {
    StringBuilder field = new StringBuilder("IHaveFieldToo");
}

class WhoWins implements IHaveField, IHaveFieldToo {
    public void print() {
        // RUNTIME ERROR
        // error: reference to field is ambiguous, both variable field in IHaveField and variable field in IHaveFieldToo match
        System.out.println(field);
    }
}
```

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

```java
// TODO: example
```

## Virtual methods

TODO

## The mandatory polymorphic parameter passing example

TODO

## Study / review

- The 5 conditions for overriding
- The 5 conditions for hiding
- What method is called when method is overriden / hidden.
- What variable is accessed when variable is hidden.
- The rules that govern abstract classes
- The rules that govern interfaces
- Multiple inheritance
- Default interface methods
- Static interface methods
- Kinds of methods you can have in an interface.
