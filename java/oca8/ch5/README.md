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
following example.

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
3:     // A call to Car() will happen before
4:     // executing the following line.
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

For the third and fourth conditions, consider the following example.

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
method `getDrivers()`.

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
for overloading apply.  For example:

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

In inherited code, a given call to a method that is overrided executes
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
    public static int instanceCount = 0;
    private String brand = "Volkswagen";
    public String toString() {
        return "Car " + brand + "-" + instanceCount;
    }
}

class RacingCar extends Car {
    private long instanceCount = 1;
    public static StringBuilder brand = new StringBuilder("Alfa Romeo");
    public String toString() {
        return "RacingCar " + brand + "-" + instanceCount + " (parent brand " + super.brand + " and parent count " + super.instanceCount + ")";
    }
    public static void main(String[] args) {
        // prints "Car Volkswagen-0"
        System.out.println(new Car());
        // prints "RacingCar Alfa Romeo-1 (parent brand Volkswagen and parent count 0)"
        System.out.println(new RacingCar());
        // *** non-static variable instanceCount cannot be referenced from a static context
        //System.out.println("instanceCount = " + instanceCount);
    }
}
```

## Declaration of abstract classes and methods

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
class Worker {
...
    // following line gives "error: illegal combination of modifiers: abstract and final"
    protected final abstract void task();
...
}
```

A method may not be abstract and private.

```java
class Worker {
...
    // the following line gives "error: illegal combination of modifiers: abstract and private"
    private abstract void task();
...
}
```

## Implementation of abstract methods

The first 4 rules for overriding methods apply to the implementation
of an abstract method. Consider the following abstract class.

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

2. The method must be at least as accessible as the abstract
   method. For example, the following declaration attempts to
   assign weaker access privileges and thus fails to compile.

   ```java
   class NumberPrinter extends Printer {
     private int document() {
         return 1;
     }
   }
   ```

3. Each exception must be covariant wrt. some exception thrown
   by the abstract method. For example, the following method
   fails to compile because abstract method does not throw
   Exception.

   ```java
   class NumberPrinter extends Printer {
     protected String document() throws Exception {
         throw new Exception("w00t");
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

## Something something

Given an abstract class and an abstract subclass, the subclass may not
provide any implementation for the abstract methods.

TODO: example

Given an abtract class and a concrete subclass, the subclass must
implement all abstract methods.

TODO: example of two abstract classes and one concrete class
TODO: example where one of the abstract methods is implemented by the
      second abstract class


## Study

- The 5 conditions for overriding
- The 5 conditions for hiding
- What method is called when method is overriden / hidden.
- What variable is accessed when variable is hidden.
