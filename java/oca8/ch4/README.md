# Chapter 4

## TODO

- What are the rules for assignment of primitive types?
  - Why is `byte task() { return 1+1; }` legal and `byte task() { byte b = 1; return b+b; }` is illegal?
- Does default package access correspond to protected rule for package access?

## Syntax of method declaration

```java
public final void sleep(long ms) throws InterruptedException {
  // do the sleeping
}
```

```
MD ::=
  | AM{0,1} OS{0,1} MDaux1
  | AM{0,1} ASS RT MDaux2;

MDaux1 ::=
  | void  MDaux2 MB(void)
  | byte  MDaux2 MB(byte)
  | short MDaux2 MB(short)
  | int MDaux2 MB(int)
  | long MDaux2 MB(long)
  | String MDaux2 MB(String)
  | byte[] MDaux2 MB(byte[])
  | short[] MDaux2 MB(short[])
  | int[] MDaux2 MB(int[])
  | long[] MDaux2 MB(long[])

MDaux2 ::= MN(PL{0,1}) OEL{0,1}

MD: method declaration
AM: access modifier
OS: optional specifier
ASS: abstract specifier + specifiers
RT: return type
MN: method name
PL: parameter list
OEL: optional exception list
MB: method body

AM ::= private | protected | public
OS ::= static | final | static final | final static
ASS ::= abstract | abstract OS | OS abstract | static abstract final | final abstract static
RT ::= void | T
MN ::= I
PL ::= T I | T I, PL
OEL ::= throws EL
MB(void) ::= { } | { return; }
MB(t) ::= { return V(t); }
EL ::= E | E, EL
T ::= byte | short | int | long | String | StringBuilder ...
V(byte) ::= 1
V(short) ::= 1
...
I ::= p | param | a1 | task ...
E ::= InterruptedException | IndexOutOfBoundsException | IllegalArgumentException | ...
```

## Access Modifiers

More restrictive to least restrictive.

1. `private`: Member is only accessible within the same class.
2. default access: (aka package private access.) Member is only accessible within the same package.
3. `protected`: Member is only accessible (oh boy, define accessible) within the same package and subclasses of given class.
4. `public`: Member is accessible everywhere.

### Table 4.2: Access modifiers

*TODO*

### Protected

0. ~~A piece of code in this class can access a member in a superclass in a different package if that member is protected.~~
1. ~~A given class C may access protected members of superclass via any reference of type C or any subclass of C that is set.~~
2. ~~A given class C may not access protected members of superclass via a reference of type any superclass of C.~~
3. ~~What happens when B and C extend A, B and C are in a different package than A, and B tries to access a protected member of A via a reference of type C?~~
4. ~~A given protected member of class C is inherited with access modifier protected by subclasses of C.~~
5. ~~Code in a class C may access any protected member of C by means of a reference of type C or any of its subtypes.~~
6. Access to protected members is allowed only when one of the following conditions is satisfied.
  1. When Loc and Decl are in same package, Loc can do the following.
     1. Call a method of the same name by means of a reference that inherits method.
     2. Read/write field by means of a reference that inherits field.
  2. When Loc is in a class that inherits member, Loc can do the following.
     1. Call a method of the same name by means of a reference R that
     inherits method and is subtype of Loc. Corresponds to test case
     ProtectedMemberAccess1.
     2. Read/write field by means of a reference that inherits field and
     is a subtype of Loc.

## Optional Specifiers

Optional specifiers can appear before the access modifier.

Relevant optional specifiers are the following.

- `static`
- `abstract`
- `final`
- `synchronized`
- `native`
- `strictfp`

## Return type

The return value must fit into the return type.

When the return type is byte or short and you return an integer literal, Java does not issue a compile error.

```java
public static byte task() { return 1; }
```

Same happens when you assign an integer literal to a variable of type short or byte.
```java
public static byte task() {
  byte b = 2;
  b = 1;
  return b;
}
```

Examples that pass.

```java
public static byte task1() { return 1 + 1; }
public static byte task2() { return (short) 1; }
public static byte task3() { return (short) (1 + 1); }
```

Examples that **do not** pass.

```java
public static byte task() {
  byte b = 1;
  return b + b;
}
```

## Method name

Follows the same rules for identifiers.
Identifiers may only consist o letters, number,s $ and _.
The first character is not allowed to be a number.


## Parameter list

Always write a pair of parenthesis.

## Optional exception list

## Method body

## Vararg parameters

- A given vararg parameter and an array parameter are different in that you can only inline array elements in the case of vararg (*TODO* example p. 192).
- Every vararg parameter is the last element in a method's parameter list.
- It is possible to pass null instead of an array.
- Given `task(int[] vararg)`, do the following calls compile and run?
  - `task(null)`
  - `task([])`
  - `task(new int[] {1,2})`
- What are the rules for initialization of fields that are arrays?

## Exercise: private and package access

- pond.duck
  - FatherDuck: everything is private.
  - BadDuckling: tries to call a method from FatherDuck.
  - MotherDuck: almost everything is package.
  - GoodDuckling: uses what MotherDuck gives.
- pond.swan
  - BadGygnet: tries to access a method from MotherDuck

## Exercise: protected access

- pond.shore
  - Bird
  - BirdWatcher
- pond.goose
  - Gosling (extends Bird)
  - Goose (extends Bird)
- pond.inland
  - BirdWatcherFromAfar
- pond.swan
  - Swan (extends Bird)
- pond.duck
  - GooseWatcher 

## Exercise: public access

- pond.duck
  - DuckTeacher
- pond.goose
  - LostDuckling 

# Purposes of static methods

1. Code that does not require state.
2. Code that operates on shared state.

# Ways to call static methods

For given class.

1. Name of class + period + method
2. Reference of type of class + period + method (even if reference is not set (*TODO* example) or null (*TODO* example)).


# Reference instance member from static code

Don't do that.

# Initialization of static and instance fields

Default values are the same for static and instance fields.

# Constants

Constants are fields declared with specifiers `static final`.

*TODO* example where you mutate object by means of a constant reference.

# Static initialization

Prepend `static` to initializer.

- *TODO* example: initialize constant
- *TODO* example: declare constant and don't initialize it
- *TODO* example: reassign constant

# Static imports

In a given class, static methods of the class have preference over imported static methods.

*TODO* Does the same apply to static fields?

*TODO* What happens when you import two static members that have the same name?

# Parameter passing

Java is "pass-by-value".

Assigning a parameter does not change value of any corresponding variable at the calling location.

*TODO* example

Mutating the state of a parameter does change value of any corresponding variable at the calling location.

*TODO* example

# Return value

You may ignore the return value of a call.

*TODO* example: ignore a primitive

*TODO* example: ignore a reference

# Overloading methods

A method name is overloaded when the following conditions are met. (*TODO* check.)

1. For a given class, there are at least two different method declarations with the given method name.
2. For each method declaration, the parameter list corresponds to a sequence of types that is different than any other sequence.

## A vararg parameter and an array parameter are considered the same for overloading

*TODO* example

## Autoboxing

A method that takes autoboxed reference type works fine with corresponding primitive.

*TODO* example

Overloaded method that varies primitive parameter type to corresponding class disables autoboxing.

*TODO* example

## Java picks the closest supertype for a given actual parameter

*TODO* example on references p. 193.

*TODO* example on primitive types p. 193

## Precedence of overloaded methods

Given an overloaded method, Java considers declarations with similar parameter lists in the following order.

1. Exact match by type
2. Supertype of primitive type
3. Autoboxed type
4. Varargs

*TODO* example p. 194

# Autoboxing only works for corresponding reference type

Java does not convert `int` to `Long` for you.

*TODO* example p. 194

## Declare a constructor

Method declaration meets the following two conditions.

1. The method name is the name of the class.
2. There is no return type.

# A class may expose no constructor

Just declare any constructor and make it private, for example:

```java
class WhatIsGoingOn {
  private WhatIsGoingOn() { }
}
```

# Overload constructor

Declare two constructors with different list of parameters.

# Call a constructor from another

Call`this` with corresponding parameters in the first statement of the
calling constructor. *TODO* example.

# Final instance fields

*TODO* example: initialize final field in constructor.
*TODO* example: declare final field and don't initialize it.
*TODO* example: reassign final field

# Order of initialization

On program start.

1. Initialize superclass
2. Static variable declarations and static initializers in the order they appear in the file.

On instantiation.

3. instance variable declarations and instance initializers int eh order they appears in the file.
4. Constructor

*TODO* example: multiple classes w/ static fields and initializers
*TODO* example: static initializers vs main method
*TODO* example like the one in p. 203

# Encapsulation

Encapsulation means construct your class such that the state of a given instance is controlled exclusively through exposed methods. The way of doing that is the following.

1. Make instance fields private.
2. Make public the methods that manipulate those fields (including but not limited to getters/setters).

# JavaBeans conventions for encapsulation

1. Properties are private.
2. A given getter method begins with `is` or `get` if the corresponding property is `boolean`.
3. A given getter method begins with `get` if the corresponding property is not `boolean`.
4. Setter methods begin with `set`.
5. The name of a getter/setter is in camelCase.

## Making your class immutable is an alternative to encapsulation

When you require that changes to a given instance are controlled, either encapsulate state or make the instance immutable.

# Immutable classes

1. Allow caller to set state.
2. Omit setters.

*TODO* example of immutable class.

# Defensive copy

*TODO* example of class that appears to be immutable but is not.

Defensive copy is copying any mutable object passed to a constructor or returned from a setter so that state of object is not changed by means a reference to an object that is part of the state.

*TODO*  convert previous example in immutable class by means of defensive copy.

# Lambda expressions

Syntax w/o optional parts.

```java
c -> c.isBrandNew()
```

Syntax with optional parts

```java
(Car c) -> { return c.isBrandNew(); }
```

*TODO* examples of valid lambdas.

# What variables can my lambda access?

*TODO* investigate list

# Predicates and functional interfaces

An interface with a single boolean method is a _functional interface_.
A predicate written as lambda expression corresponds to a functional interface.
Don't declare your own functional interface, use interface `java.util.function.Predicate<T>`.

*TODO* example

# (Array)List's `removeIf` method

*TODO* example of `removeIf`.

# Practice this

- Identify correct and incorrect method declarations.
- Identify access to a member from a context that is fobidden by corresponding access modifier.
- Recognize valid and invalid uses of static imports.
- Identify illegal calls to static / instance methods.
- Evaluate initialization code + constructors.
- Recognize when a class is properly encapsulated.
- Write simple predicates with lambda expressions.
