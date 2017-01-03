# Chapter 4

## Syntax of method declaration

## Access Modifiers

More restrictive to least restrictive.

1. `private`: Member is only accessible within the same class.
2. default access: (aka package private access.) Member is only accessible within the same package.
3. `protected`: Member is only accessible within the same package and subclasses of given class.
4. `public`: Member is accessible everywhere.

### Table 4.2: Access modifiers

*TODO*

### Protected

0. A piece of code in this class can access a member in a superclass in a different package if that member is protected.
1. A given class C may access protected members of superclass via any reference of type C or any subclass of C that is set.
2. A given class C may not access protected members of superclass via a reference of type any superclass of C.
3. What happens when B and C extend A, B and C are in a different package than A, and B tries to access a protected member of A via a reference of type C?
4. When a protected member is inherited by a class, the member is protected for the class. 

## Optional Specifiers

Optional specifiers can appear before the access modifier.

## Return type

The return value must fit into the return type.

## Method name

Follows the same rules for identifiers.

## Parameter list

Always write a pair of parenthesis.

## Optional exception list

## Method body

## Vararg parameters

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

A method name is overloaded when the following conditions are met.

1. For a given class, there are at least two different method declarations with the given method name.
2. For each method declaration, the parameter list corresponds to a sequence of types that is different from any other sequence.


