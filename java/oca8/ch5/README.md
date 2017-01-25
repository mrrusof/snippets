# Chapter 5

** Objectives **

- Describe inheritance and its benefits (w00t).
- Develop code that demonstrates the use of polymorphism, including overriding and object type versus reference type.
  - Understand what polymorphism is.
- Determine when casting is necessary.
- Use super and this to access objects and constructors.
- Use abstract classes and interfaces.

## Inheritance

Inheritance or subclassing is the process by which a child class automatically includes public and protected members from the corresponding parent.
Other members are also included but may not be accessible to the child class.

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

There may be only one public class in a given file and the file name must be that of the public class.
The corresponding rule applies to interfaces.

## When you don't indicate a superclass, your class extends java.lang.Object

Consider the following code.

```java
public class Car { }
```

The semantics of that class is the same as the semantics of the following.

```java
public class Car extends java.lang.Object { }
```

## Use of `super()`

You may only call `super(...)` in the first location of the body of any given constructor, for example:

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

If a given constructor does not call any version of `super(...)` or `this(...)`, Java will call `super()` by default. Consider the following example.

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

Given that constructor `Car()` is called in line 3 and there is no such constructor in class `Car`, the code does not compile.
A way to remove the compile error is to declare constructor `Car()` as follows.

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

For example, the following will not compile because there is no constructor `RacingCar(String)`.

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

A class that extends another may use public and protected members. For example:

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