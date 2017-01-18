# Chapter 2

## Rules for numeric promotion

The following rules apply to binary arithmetic operators.

1. Operands of type `byte`, `short`, and `char` are always promoted to `int`.
2. When one operand is integral and the other is floating point, the integral value is automatically promoted to the type of the floating point value.
3. When two operands belong to different data types, the smaller type is promoted to the larger type.
4. After operands are promoted, the type of the arithmetic expression is the type of the operands.

