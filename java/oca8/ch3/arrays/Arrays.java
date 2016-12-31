import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;

public class Arrays {
    public static void main(String args[]) {
        {
            out.println("Create an array of ints (1)");
            int[] nn = new int[3];
            nn[0] = 1;
            nn[1] = 2;
            //            nn[3] = 3;
            for(int n : nn)
                out.print(n + " ");
            out.println();
        }
        {
            out.println("Create an array of ints (2)");
            int    [    ] nn = new int[] { 1, 2, 3 }; // anonymous array
            for(int n : nn)
                out.print(n + " ");
            out.println();
        }
        {
            out.println("Create an array of ints (3)");
            int nn[] = new int[] { 1, 2, 3 }; // anonymous array
            for(int n : nn)
                out.print(n + " ");
            out.println();
        }
        {
            out.println("Create an array of ints (3)");
            int nn   [     ] = new int[] { 1, 2, 3 }; // anonymous array
            for(int n : nn)
                out.print(n + " ");
            out.println();
        }
        {
            out.println("Create multiple arrays of ints (1)");
            int [ ] ids, types = { 3, 2, 1 };
            for(int t : types)
                out.print(t + " ");
            out.println();
        }
        {
            out.println("Create one array of ints and one int (1)");
            int ids [   ] = { 3, 2, 1 }, type = 1;
            out.println(type);
            for(int i : ids)
                out.print(i + " ");
            out.println();
            out.println(ids[type]);
        }
        {
            out.println("Array of ref type (1)");
            String [] bugs = new String[] { "cricket", "beetle", "ladybug" };
            for(String s : bugs)
                out.print(s + " ");
            out.println();
        }
        {
            out.println("Cast array (1)");
            String[] strings = { "hello" };
            Object[] objects = strings;
            out.println("strings == objects = " + (strings == objects)); // true
            strings = (String[])objects; // cast array
            out.println("strings == objects = " + (strings == objects)); // true
        }
        {
            out.println("Cast array (2)");
            Object[] objects = { "hello", 1 , 2f, true};
            for(Object o : objects)
                out.print(o + " ");
            out.println();
        }
        {
            out.println("Cast array (3)");
            String [] strings = { "hola" };
            Object[] objects = strings;
            String[] againStrings = (String[]) objects;
            //againStrings[0] = new StringBuilder(); // does not compile
            //objects[0] = new StringBuilder("world"); // runtime error
            out.println(objects[0]);
            out.println(againStrings[0]);
        }
        {
            out.println("Using arrays (1)");
            String cars[] = new String[6];
            out.println(cars.length);
        }
        {
            out.println("Using arrays (2)");
            String cars[] = new String[6];
            for(String car : cars)
                out.print(car + ",");
            out.println();
        }
        {
            out.println("Using arrays (3)");
            String [ ] cars = new String[6];
            out.println(cars[0]);
            // out.println(cars[6]);
            // out.println(cars.length() - 1);
        }
        {
            out.println("Using class Arrays (1)");
            int [ ] numbers = { 4,3,9 };
            java.util.Arrays.sort(numbers);
            for(int n : numbers)
                out.print(n + " ");
            out.println();
        }
        {
            out.println("Arrays.sort (1)");
            String[] strings = { "10", "9", "100" };
            java.util.Arrays.sort(strings);
            for(String s : strings)
                out.print(s + " ");
            out.println();
        }
        {
            out.println("Arrays.binarySearch (1)");
            int[] numbers = { 9, 5, 2, 3 };
            out.println("numbers = " + java.util.Arrays.toString(numbers));
            out.println(java.util.Arrays.binarySearch(numbers, 2));
            java.util.Arrays.sort(numbers);
            out.println("numbers = " + java.util.Arrays.toString(numbers));
            out.println(java.util.Arrays.binarySearch(numbers, 2));
            out.println(java.util.Arrays.binarySearch(numbers, 6)); // -3
        }
        {
            out.println("Arrays.binarySearch (2)");
            int[] numbers = { 2, 3, 5};
            out.println(java.util.Arrays.binarySearch(numbers, 4));
        }
        {
            out.println("arrays.binarySearch (3)");
            int[] nn = { 3,2,1 };
            out.println(java.util.Arrays.binarySearch(nn, 2));
            out.println(java.util.Arrays.binarySearch(nn, 3));
        }
        { 
            out.println("matrices (1)");
            int [] [ ] m1;
            int m2 [   ] [];
            int [] m3 []; // 2d matrix
            int[] m4 [], m5 [][]; // 2d matrix and 3d matrix
        }
        {
            out.println("matrices (2)");
            int [] m [] [  ] = new int [1] [2]   [ 3];
            int [] [][] m2 = {{{1,2,3},{4,5,6}}};
            m = m2;
            for(int [] i [  ]: m)
                for(int j [] : i) {
                    for(int n : j)
                        System.out.print(n + " ");
                    System.out.println();
                }
        }
        {
            out.println("matrices (3)");
            int [ ] m[] = {{9,3,4}, {} };
            for(int i = 0; i < m.length; i++)
                out.println("m[" + i + "] = " + m[i].length);
            // for(int i = 0; i < m.length; i++) {
            //     for(int j = 0; j < m[0].length; j++)
            //         out.print(m[i][j] + " ");
            //     out.println();
            // }
        }
        {
            out.println("matrices (4)");
            int m[][] = new int[2][];
            m[0] = new int[1];
            m[1] = new int[2];
            System.out.println(m[0][0]);
        }
        {
            out.println("matrices (5)");
            Object m[][] = new Object[2][];
            m[0] = new String[1];
            out.println(m[0][0]);
        }
        {
            out.println("Using matrices (1)");
            int[] [ ] m2 = new int[3][2];
            THE_LOOP: for(int i = 0; i < m2.length; i++) {
                for(int j = 0; j < m2[i].length; j++)
                    if(i == 1)
                        break THE_LOOP;
                    else
                        out.print(m2[i][j] + " ");
                out.println();
            }
        }
        {
            out.println("Using matrices (2)");
            int [][]m2= new int[3][2];
            THE_LOOP: for(int [] m3 : m2) {
                for(int i : m3)
                    if(i == 0)
                        break THE_LOOP;
                    else
                        out.print(i + " ");
                out.println();
            }
        }
        {
            out.println("ArrayList (1)");
            List l1 = new ArrayList();
            List l2 = new ArrayList(3);
            List l3 = new ArrayList(l2);
            //List<int> l4 = new ArrayList<>(); // does not compile
            List<Integer> l5 = new ArrayList<>();
            List<Integer> l6 = new ArrayList();
            l6.add(1);
            out.println("l6 = " + l6);
            // l6.add("true"); // does not compile
            // List<Integer> l7 = new ArrayList<Object>(); // does not compile
            // List<Object> l8 = (List<Object>)new ArrayList<Integer>(); // does not compile
        }
        {
            out.println("ArrayList (2)");
            List<Integer> l1 = new ArrayList<>();
            l1.add(1);
            out.println(l1.toString());
            List<String> l2 = new ArrayList<>();
            String[] ss = {"hello", "world"};
            for(String s : ss)
                l2.add(s);
            out.println(l2.toString());
        }
        {
            out.println("ArrayList (3)");
            List<Integer> l1 = new ArrayList<>();
            for(int i = 1; i <= 10; i++)
                l1.add(i);
            out.println(l1.toString());
            List<Integer> l2 = new ArrayList<>();
            for(int i = 1; i <= 10; i++)
                l2.add(0, i); // insert in position 0
            out.println(l2);
        }
        {
            out.println("ArrayList (4)");
            ArrayList list = new ArrayList(); // list of objectssssszzzZZZZzzzZZZzzz
            list.add("hawk");
            list.add(Boolean.TRUE);
            System.out.println(list);
        }
        {
            out.println("ArrayList.add (1)");
            ArrayList<String> list = new ArrayList<>();
            list.add("sparrow");
            //list.add(Boolean.TRUE); // does not compile
            // ArrayList<String> l = (new ArrayList<String>()).add("hola"); // does not compile
        }
        {
            out.println("ArrayList.add (2)");
            List<String> birds = new ArrayList<>();
            birds.add("hawk"); // [hawk]
            birds.add(1, "robin"); // [hawk, robin]
            birds.add(0, "blue jay"); // [blue jay, hawk, robin]
            birds.add(1, "cardinal"); // [blue jay, cardinal, hawk, robin]
            out.println(birds);
        }
        {
            out.println("ArrayList.add (3)");
            List<String> a = new ArrayList<>();
            a.add("hello"); // [hello]
            a.add(1, "world"); // [hello, world]
            a.add(0, "well"); // [well, hello, world]
            out.println(a);
        }
        {
            out.println("ArrayList.remove (1)");
            List<String> l = new ArrayList<>();
            // boolean remove(Object o) // first object that is equal
            // E remove(int index) // remove given pos
            l.add("hawk"); // [hawk]
            l.add("hawk"); // [hawk, hawk]
            out.println(l.remove("cardinal")); // false
            out.println(l.get(0) == l.get(1)); // true
            out.println(l.remove("hawk")); // true, leaves [hawk]
            out.println(l.remove(0)); // hawk, leaves []
            // l.remove(100); // index out of bounds exception
            out.println(l); // []
            out.println(l.isEmpty());
        }
        {
            out.println("ArrayList.set (1)");
            // E set(int index, E newElement)
            List<String> l = new ArrayList<>();
            l.add("hello");
            l.add("world");
            l.add("wadup");
            l.set(2, "!");
            out.println(l);
        }
        {
            out.println("ArrayList.set (2)");
            List<String> l = new ArrayList<>();
            l.add("hawk"); // [hawk]
            out.println(l.size()); // 1
            l.set(0, "robin"); // [robin]
            out.println(l.size()); // 1
            // l.set(1, "robin"); // index out of bounds
        }
        {
            out.println("ArrayList.clear (1)");
            List<String> l = new ArrayList<>();
            l.add("hello");
            l.add("world");
            l.add("!");
            l.clear();
            out.println(l);
        }
        {
            out.println("ArrayList.contains (1)");
            List<String> l = new ArrayList<>();
            l.add("hello");
            l.add("world");
            l.add("!");
            out.println(l.contains("hawk")); // false
            out.println(l.contains("hello")); // true
        }
        {
            out.println("ArrayList.contains (2)");
            List<String> l = new ArrayList<String>();
            l.add(new String("hello"));
            out.println(l); // [hello]
            out.println(l.contains("hello")); // true
        }
        {
            out.println("ArrayList.contains (3)");
            List<StringBuilder> l = new ArrayList<>();
            l.add(new StringBuilder("hello"));
            out.println(l); // [hello]
            out.println(l.contains("hello")); // false, because contains(Object o)
            StringBuilder sb = new StringBuilder("hello");
            out.println(l.contains(sb)); // false, because not the same object
        }
        {
            out.println("ArrayList.equals (4)");
            List<String> l1 = new ArrayList<>();
            l1.add("hello");
            List<String> l2 = new ArrayList<>();
            l2.add(new String("hello"));
            out.println(l1.equals(l2)); // true
        }
        {
            out.println("ArrayList + primitives (1)");
            List<Byte> l = new ArrayList<>();
            //l.add(1); // does not compile
            //l.add((Byte) 1); // does not compile
            l.add((byte) 1);
        }
        {
            out.println("ArrayList + primitives (2)");
            List<Float> l = new ArrayList<>();
            // l.add(1.0); // does not compile
            // l.add(2d); // does not compile
            l.add(1.0f);
            l.add(2f);
            // l.add(1); //does not compile
            out.println(l.get(0));
            double d = l.get(0);
            l.add(null);
            // d = l.get(2); // does not compile
            Float f = l.get(2);
            // Double dd = (Double)l.get(2); // does not compile
        }
        {
            out.println("ArrayList + primitives (3)");
            List<Integer> l = new ArrayList<>();
            l.add(1);
            l.add(2);
            l.remove(1);
            out.println(l);
            List<Integer> l2 = new ArrayList<>();
            l2.add(1);
            l2.add(2);
            l2.remove((Integer) 1);
            out.println(l2);
            List<Integer> l3 = new ArrayList<>();
            l3.add(1);
            l3.add(2);
            l3.remove(new Integer(1));
            out.println(l3);
        }
        {
            out.println("Convert between array and list (1)");
            out.println("Convert ArrayList into array");
            List<String> l = new ArrayList<>();
            l.add("hawk");
            l.add("robin");
            Object[] oArray = l.toArray(); // to array of supertype
            out.println(oArray.length);
            String[] sArray = l.toArray(new String[0]); // to array of same type
        }
        {
            out.println("Convert between array and list (2)");
            out.println("Convert ArrayList into array of same underlying type");
            List<Integer> l = new ArrayList<>();
            l.add(1);
            // Integer[] a = (Integer[])l.toArray(); // does not compile
            Integer[] a = l.toArray(new Integer[0]); // this is "the right way"
            out.println(a[0]);
            List<String> sl = new ArrayList<>();
            sl.add("Hello");
            String[] sa = sl.toArray(new String[0]);
            out.println(sa[0]);
        }
        {
            out.println("Convert between array and list (3)");
            out.println("Convert ArrayList into array of same underlying type");
            List<String> l = new ArrayList<>();
            l.add("hawk");
            l.add("robin");
            Object[] oA = l.toArray();
            out.println("oA = " + java.util.Arrays.toString(oA));
            out.println(oA.length); // 2
            String[] sA = l.toArray(new String[0]);
            out.println("sA = " + java.util.Arrays.toString(sA));
            out.println(sA.length); // 2
            // a change to one array does not affect the other...
            oA[0] = null;
            out.println("oA = " + java.util.Arrays.toString(oA));
            out.println("sA = " + java.util.Arrays.toString(sA));
        }
        {
            out.println("Convert between array and list (4)");
            List<String> l = new ArrayList<>();
            l.add("hello");
            Object[] oA = l.toArray();
            String[] sA = l.toArray(new String[0]);
            oA[0] = null;
            // a change to an array created from a given ArrayList does
            // not change another array created from the same ArrayList
            System.out.println(java.util.Arrays.toString(sA));
        }
        {
            out.println("Convert between array and list (5)");
            out.println("Convert array into a fixed length List of same underlying type");
            String[] a = {"hawk", "robin"};
            List<String> l = java.util.Arrays.asList(a);
            l.set(0, "1"); // changes array a too
            for(String s : a)
                out.print(s + " ");
        }
        {
            out.println("Convert between array and list (6)");
            out.println("Convert array into a fixed length List of same underlying type");
            String[] a = { "hawk", "robin" }; // [hawk, robin]
            List<String> l = java.util.Arrays.asList(a); // fixed-size list
            out.println(l.size());
            l.set(1, "test"); // [hawk, test]
            a[0] = "new"; // [new, test]
            for(String b : a) out.print(b + " "); // new test
            //l.remove(1); // UnsupportedOperationException
        }
        {
            out.println("Convert sequence of objects to fixed length List");
            List<String> l = java.util.Arrays.asList("hello", "world");
            out.println(l); // [hello, world]
            //l.remove(0); // UnsupportedOperationException
        }
        {
            out.println("Sort ArrayList");
            List<Integer> n = new ArrayList<>();
            n.add(99);
            n.add(5);
            n.add(81);
            java.util.Collections.sort(n);
            out.println(n);
        }
    }
}
