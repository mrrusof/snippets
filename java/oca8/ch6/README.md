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

`Error` is a fatal error.

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
