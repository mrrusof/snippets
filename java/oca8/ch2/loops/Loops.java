import java.util.List;
import java.util.ArrayList;

public class Loops {
    public static void main(String... args) {
        {
            int i = 1;
            do
                System.out.println(i + " boring");
            while(i++ < 5);
        }

        // write the previous do-while-loop as a for-loop
        for(int i = 1; i < 5; i++)
            System.out.println(i + " boring");

        // don't use the update statement...
        for(int i = 1; i++ < 5; )
            System.out.println(i + " boring");

        System.out.println("separator");

        // your vanilla example of multiple variable initialization and stuff...
        {
            int i, j;
            for(i = 1, j = 0; i < 5; i++) {
                j++;
                System.out.println((i + j) + " boring");
            }
        }

        // vanilla waste of time
        for(int i = 0; i < 10; i++)
            System.out.print(i + " ");

        // vanilla infinite loop
        // for(;;)
        //     System.out.println("swerving out of control,eaeaeae");

        // multiple terms , again...
        {
            System.out.println("\nmultiple terms again...");
            int x = 0;
            for(long y = 0, z = 4; x < 5 && y < 10; x++, y++)
                System.out.print(y + " ");
        }

        // compilation errors...
        {
            int x = 0;
            // for(long y = 0, x = 4; x < 5 && y < 10; x++, y++) { // x is already declared
            //     System.out.println("whatever");
            // }

            // for(long y = 0, int z = 4; x < 5; x++) // second term in initialization statement is not an assignment...
            //     System.out.println("whatever");
        }

        // for-each loop
        {
            System.out.println();
            final String[] names = new String[3];
            names[0] = "Juan";
            names[1] = "Pedro";
            names[2] = "Maria";
            for(String n : names)
                System.out.println(n);

            List<String> values = new ArrayList<String>();
            values.add("Carlos");
            values.add("Marcos");
            values.add("Ricardo");
            for(String v : values)
                System.out.print(v + " ");
            System.out.println();
 
            String[] names2 = new String[3];
            for(String s : names2)
                System.out.println(s);
        }

        {
            int[] values = new int[3];
            values[0] = 10;
            values[1] = new Integer(5);
            values[2] = 15;
            for(int i = 1; i < values.length; i++)
                System.out.print(values[i]-values[i-1] + ", ");
            System.out.println();
        }

        // nested loops
        {
            int[][] matrix = {{5,2,1,3},{3,9,8,9},{5,7,12,7}};
            for(int[] array : matrix) {
                for(int i : array)
                    System.out.print(i + "\t");
                System.out.println();
            }
        }
        {
            int x = 20;
            while(x > 0) {
                do
                    x -= 2;
                while(x > 5);
                x--;
                System.out.print(x + "\t");
            }
            System.out.println();
        }

        // break statements with labels...
        {
            int[][] l = {{1,13,5},{1,2,5},{2,7,2}};
            int searchValue = 2;
            int x = -1;
            int y = -1;
            THE_LOOP: for(int i = 0; i < l.length; i++)
                for(int j = 0; j < l[i].length; j++)
                    if(l[i][j] == searchValue) {
                        x = j;
                        y = i;
                        break THE_LOOP;
                    }
            if(x == -1)
                System.out.println("Value " + searchValue + " not found.");
            else
                System.out.println("Value " + searchValue + " found at (" + x + "," + y +").");
        }

        // continue statements with labels...
        // continue with label interrupts the current iteration and starts a new iteration of the loop corresponding to the label.
        {
            THE_LOOP: for(int a = 1; a <= 4; a++)
                for(char x = 'a'; x <= 'c'; x++) {
                    if(a == 2 || x == 'b')
                        continue THE_LOOP;
                    System.out.print(" " + a + x);
                }
            System.out.println();
        }

        // equivalent while loop
        {
            int a = 1;
            THE_LOOPY: while(a <= 4) {
                for(char x = 'a'; x <= 'c'; x++) {
                    if(a == 2 || x == 'b') {
                        a++;
                        continue THE_LOOPY;
                    }
                    System.out.print(" " + a + x);
                }
                a++;
            }
            System.out.println();
        }

        // by the way, this compiles...
        SOMETHING_SOMETHING: if(true)
            System.out.println("whatever");
    }
}
