# Summary

## Create a String

1. Write a literal: `"hello"`
2. Create a new instance from a literal: `new String("hello")`
3. Create from another String: `"hola".substring(2)`

## The 13 relevant methods of String

1. `char charAt(int index)`
2. `indexOf`
  1. `int indexOf(int char)`
  2. `int indexOf(int char, int fromIndex)`
  3. `int indexOf(String str)`
  4. `int indexOf(String str, int fromIndex)`
3. `substring`
  1. `String substring(int beginIndex)`
  2. `String substring(int beginIndex, int endIndex)`
4. `String toLowerCase()`
5. `String toUpperCase()`
6. `boolean equals(Object o)`
7. `boolean equalsIgnoreCase(String str)`
8. `boolean startsWith(String prefix)`
9. `boolean endsWith(String suffix)`
10. `boolean contains(String str)`
11. `replace`
  1. `String replace(char oldChar, char newChar)`
  2. `String replace(CharSequence oldStr, CharSequence newStr)`
12. `String trim()`
13. `String concat(String suffix)`
14. `int length()`

## Create a StringBuilder

1. Create default StringBuilder: `new StringBuilder()`
2. Create from a String: `new StringBuilder("hello")`
3. Create with given capacity: `new StringBuilder(10)`

## The 11 relevant methods of StringBuilder

1. `char charAt(int index)`
2. `indexOf`
  1. `int indexOf(String str)`
  2. `int indexOf(String str, int fromIndex)`
3. `int length()`
4. `substring`
  1. `String substring(int start)`
  2. `String substring(int start, int end)`
5. `StringBuilder append(String str)`
6. `StringBuilder insert(int offset, String str)`
7. `StringBuilder delete(int start, int end)`
8. `StringBuilder deleteCharAt(int index)`
9. `StringBuilder reverse()`
10. `StringBuilder toString()`
11. `boolean equals(Object o)`

## Declare an array

1. Declare array
  1. `int[] a`
  2. `int a[]`
2. Declare matrix
  1. `int[][] m`
  2. `int[] m[]`
3. Declare array and matrices on the same line
  1. `int[] m[], a`, `m` is a matrix and `a` is an array.

## Create an array

1. Create an array of given size
  1. Array: `new int[3]`
  2. Rectangular matrix: `new int[3][2]`
  3. Uneven matrix: `new int[3][]`
2. Create an array with given contents
  1. Array: `{ 1, 2, 3 }`
  2. (Uneven) Matrix: `{{1,2}, {3}}`

## Create ArrayList

1. Create default ArrayList.
  1. Of objects: `new ArrayList()`
  2. Of given underlying class: `new ArrayList<Integer>()`
2. Create with given capacity: `new ArrayList(10)`
3. Create from given List: `new ArrayList(list)`

## The 7 relevant methods of ArrayList

1. `add`
  1. `boolean add(E element)`, `E` the underlying type of the ArrayList
  2. `void add(int index, E element)`, inserts `element` in given `index`.
2. `remove`
  1. `boolean remove(Object o)`, remove first that is equal
  2. `E remove(int pos)`, remove and return element at given `pos`.
3. `E set(int index, E new)`, return element at `index` and replace with `new`.
4. `boolean isEmpty()`
5. `int size()`
6. `void clear()`
7. `boolean contains(Object element)`

## Assign array to array of supertype

```java
Integer[] a = {1,2,3};
Object[] b = a;
```

## Assign array to array of subtype

```java
Integer[] a = {1,2,3};
Object[] b = a;
Integer[] c = (Integer[])b;
```

## Convert ArrayList to array of same underlying type

```java
ArrayList<Integer> l = new ArrayList<>();
Integer[] a = l.toArray(new Integer[0]);
```
## Convert array into fixed-length List

```java
int[] a = {4,5,1,3};
List<Integer> nn = java.util.Arrays.asList(a);
List<String> ss = java.util.Arrays.asList("one", "two", "three");
```

## Sort array

```java
int[] a = {4,5,1,3};
java.util.Arrays.sort(a); // 1,3,4,5
```

## Sort (Array)List

```java
List<Integer> l = new ArrayList<>();
int[] a = {4,5,1,3};
for(int n : a)
  l.add(n);
java.util.Collections.sort(l); // 1,3,4,5

List<Integer> m = java.util.Arrays.asList(4,5,1,3);
java.util.Collections.sort(m); // 1,3,4,5
```

## Create LocalDate from year, month, and day

```java
LocalDate d1 = LocalDate.of(2016,12,31);
LocalDate d2 = LocalDate.of(2016,Month.DECEMBER,31);
```

## Create LocalTime from hour+minute((+second)+nanosecond)

```java
LocalTime t1 = LocalTime.of(4,59);
LocalTime t2 = LocalTime.of(4,59,30);
LocalTime t3 = LocalTime.of(4,59,30,100);
```

## Create LocalDateTime from year+month+day+hour+minute((+second)+nanosecond)

```java
LocalDateTime dt1 = LocalDateTime.of(2016,12,31,4,59);
LocalDateTime dt2 = LocalDateTime.of(2016,12,31,4,59,30);
LocalDateTime dt3 = LocalDateTime.of(2016,12,31,4,59,30,100);
LocalDateTime dt4 = LocalDateTime.of(2016,Month.DECEMBER,31,4,59);
LocalDateTime dt5 = LocalDateTime.of(2016,Month.DECEMBER,31,4,59,30);
LocalDateTime dt6 = LocalDateTime.of(2016,Month.DECEMBER,31,4,59,30,100);
```

## Create LocalDateTime from LocalDate+LocalTime

```java
LocalDate d = LocalDate.of(2016,12,31);
LocalTime t = LocalTime.of(4,59);
LocalDateTime dt = LocalDateTime.of(d, t);
```

## LocalDate, LocalTime, LocalDateTime are immutable

```java
Period p = Period.ofMonths(1);
LocalDate d = LocalDate.of(2016,12,31);
d.plus(p); // d remains unchanged
```

## Create Period of days/months/years/weeks or years+months+days

```java
Period p1 = Period.ofDays(1);
Period p2 = Period.ofMonths(1);
Period p3 = Period.ofYears(1);
Period p4 = Period.ofWeeks(1);
Period p5 = Period.of(1,2,3); // years, months, days
```

## Format LocalDate/Time/DateTime w/ SHORT and MEDIUM formats

```java
import java.time.*;
import java.time.format.*;
...
java.time.format.DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
LocalDate d = LocalDate.now();
System.out.println(d.format(f));
System.out.println(f.format(d));

DateTimeFormatter f2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
LocalDateTime dt = LocalDateTime.now();
System.out.println(dt.format(f2));
System.out.println(d2.format(dt));
```

## Parse date/time

```java
import java.time.*;
import java.time.format.*;
...
DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
LocalDate d = LocalDate.parse("2016/12/31", f);

f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
LocalDateTime dt = LocalDateTime.parse("2016/12/31 04:59", f);
```
