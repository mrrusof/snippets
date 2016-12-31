import static java.lang.System.out;

public class Str {

    public static void main(String... args) {
        {
            String s1 = "1";
            String s2 = s1.concat("2");
            s2.concat("3");
            System.out.println(s2);
        }
        out.println("autoboxing");
        {
            String a = "hola"; // autoboxing(?)
            String b = "hola"; // autoboxing(?)
            System.out.println("(autoboxing) a == b = " + (a == b)); // true, string pool in action
        }
        {
            String a = "hola"; // autoboxing(?)
            String b = new String("hola"); // different instance than autoboxing one
            System.out.println("(autoboxing vs constructor) a == b = " + (a == b)); // false
        }
        {
            String a = "hola"; // autoboxing
            String b = new String(a);
            out.println("(autoboxing vs constructor 2) a == b = " + (a == b));
            out.println("(autoboxing vs constructor 2) a.equals(b) = " + a.equals(b));
        }
        {
            // the thirteen methods that you must definitely know by heart
            String a = "controller";
            out.println(a);
            out.println("a.length() = " + a.length());
            for(int i = 0; i < a.length(); i++)
                out.println("a.charAt(" + i + ") = " + a.charAt(i));
            out.println("a.indexOf('r') = " + a.indexOf('r')); // indexOf(int char)
            out.println("a.indexOf(\"la\") = " + a.indexOf("la")); // indexOf(String str)
            out.println("a.indexOf(\"ll\") = " + a.indexOf("ll"));
            out.println("a.indexOf(\"ll\", 0) = " + a.indexOf("ll", 0)); // indexOf(String str, int from)
            out.println("a.indexOf('r', 5) = " + a.indexOf('r', 5)); // indexOf(int char, int from)
            out.println("(int)'r' = " + (int)'r');
            out.println("a.indexOf(114) = " + a.indexOf(114));
            out.println("a.indexOf(114, 5) = " + a.indexOf(114, 5));
            out.println("a.substring(0) = " + a.substring(0)); // substring(int start)
            out.println("a.substring(1) = " + a.substring(1));
            out.println("a.substring(0,10) = " + a.substring(0, 10)); // substring(int start, int end)
            out.println("a.substring(1, 10) = " + a.substring(1, 10));
            out.println("a.substring(1, 9) = " + a.substring(1, 9));
            out.println("first three letters of word: " + a.substring(0, 3));
            out.println(a);
            for(int i = 0; i < 5; i++)
                if(1 < i)
                    out.print("-");
                else
                    out.print(" ");
            out.println();
            out.println("third, fourth, and fifth letters of word: " + a.substring(2, 5));
        }
        // substring() (3)
        {
            out.println("substring()");
            String string = "animals";
            out.println(string.substring(3)); // mals
            out.println(string.substring(string.indexOf('m'))); // mals
            out.println(string.substring(3, 4)); // m
            out.println(string.substring(3, 7)); // mals
            out.println("string.substring(3, 3) = \"" + string.substring(3, 3) + "\""); // ""
            // out.println(string.substring(3, 2)); // exception!, StringIndexOutOfBoundsException
            // out.println(string.substring(3, 8)); // another exception!, again StringIndexOutOfBoundsException
        }
        // toLowerCase() (4), toUpperCase() (5)
        {
            out.println("toLowerCase(), toUpperCase()");
            String a = "animals";
            out.println("a.toUpperCase() = " + a.toUpperCase());
            out.println("a = " + a);
            String b = "AnImAlS";
            out.println("b.toLowerCase() = " + b.toLowerCase());
            out.println("b = " + b);
        }
        // equals() (6), equalsIgnoreCase() (7)
        {
            out.println("equals(), equalsIgnoreCase()");
            String a = "animals";
            String b = "AnImAlS";
            out.println("a.equals(a) = " + a.equals(a));
            out.println("a.equals(b) = " + a.equals(b));
            out.println("a.equalsIgnoreCase(b) = " + a.equalsIgnoreCase(b));
            String c = "an1mals";
            out.println("a.equalsIgnoreCase(c) = " + a.equalsIgnoreCase(c));
        }
        // startsWith() (8), endsWith(9)
        {
            out.println("startsWith(String prefix), endsWith(String suffix)");
            String a = "animals";
            String b = "ani";
            out.println("a.startsWith(b) = " + a.startsWith(b));
            String c = "nim";
            out.println("a.startsWith(c) = " + a.startsWith(c));
            out.println("a.startsWith(\"A\") = " + a.startsWith("A"));
        }
        // contains() (10)
        {
            out.println("contains(String  str)");
            String a = "animals";
            String b = "nim";
            String c = "niml";
            out.println("a.contains(b) = " + a.contains(b));
            out.println("a.contains(c) = " + a.contains(c));
        }
        // replace() (11)
        {
            out.println("replace(char old, char new) or eplace(CharSequence old, CharSequence new)");
            String a = "hola";
            out.println("a.replace('o', 'e') = " + a.replace('o', 'e'));
            out.println("a = " + a);
            out.println("a.replace('o', 'e') = " + (a = a.replace('o', 'e')));
            out.println("a = " + a);
            out.println("a.replace(\"la\", \"llo\") = " + (a = a.replace("la", "llo")));
            out.println("a = " + a);
            String b = "abcabc";
            out.println("b = " + b);
            out.println("b.replace('a', 'A') = " + b.replace('a', 'A'));
        }
        // trim() (12)
        {
            String a = "\t\nani\tmals  \t\n";
            out.println("a = " + a);
            out.println("a.trim() = " + a.trim());
        }
        // method chaining example
        {
            out.println("method chaining example");
            String a = "abc";
            String b = a.toUpperCase();
            b = b.replace("B", "2").replace('C', '3');
            out.println("a=" + a);
            out.println("b=" + b);
        }
        // StringBuilder example
        {
            out.println("StrigBuilder example");
            StringBuilder a = new StringBuilder();
            for(char c = 'a'; c <= 'z'; c++)
                a.append(c);
            out.println("a = " + a);
        }
        // Another StringBuilder example
        {
            out.println("Another StringBuilder example");
            StringBuilder sb = new StringBuilder("start");
            sb.append("+middle"); // sb = "start+middle"
            StringBuilder same = sb.append("+end"); // sb = "start+middle+end"
            out.println("sb == same = " + (sb == same));
            String s = "start+middle";
            String same_maybe = s + "+end";
            out.println("s == same_maybe = " + (s == same_maybe));
            StringBuilder a = new StringBuilder("hola");
            StringBuilder b = a.append(" mundo");
            out.println("a == b = " + (a == b));
        }
        // Yet another StringBuilder example
        {
            out.println("Yet another StringBuilder example");
            StringBuilder a = new StringBuilder("abc");
            StringBuilder b = a.append("de");
            b = b.append("f").append("g");
            out.println("a = " + a);
            out.println("b = " + b);
        }
        {
            out.println("StringBuilder append() (1)");
            StringBuilder a = new StringBuilder("hello");
            StringBuilder b = a.append(" world");
            out.println("a == b = " + (a == b));
            out.println("a.equals(b) = " + a.equals(b));
        }
        // Three ways to create a StringBuilder
        {
            StringBuilder sb1 = new StringBuilder(); // default params, default capacity is 16
            StringBuilder sb2 = new StringBuilder("hola"); // w/ initial string
            StringBuilder sb3 = new StringBuilder(10); // w/ initial capacity
        }
        out.println("Relevant methods of StringBuilder");
        {
            out.println("charAt(), just like in String");
            StringBuilder sb = new StringBuilder("animals");
            for(int i = 0; i < sb.length(); i++)
                out.println("sb[" + i + "] = " + sb.charAt(i));

            out.println("indexOf(), similar to the one for String, but not the same");
            // there is no indexOf(int char) or indexOf(int char, int from) for StringBuilder
            out.println("sb.indexOf(\"a\") = " + sb.indexOf("a")); // indexOf(String str)
            out.println("sb.indexOf(\"a\", 1) = " + sb.indexOf("a", 1)); // indexOf(String str, int from)

            out.println("substring(), just like in String");
            out.println("sb.substring(1) = " + sb.substring(1)); // substring(int from)
            out.println("sb.substring(0, 6) = " + sb.substring(0, 6)); // substring(int from, int to)

            out.println("textbook example");
            {
                StringBuilder a = new StringBuilder("animals");
                String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));
                int len = sb.length();
                char ch = sb.charAt(6);
                out.println(sub + " " + len + " " + ch);
            }

            out.println("my own textbook example on StringBuffer's substring");
            {
                StringBuilder a = new StringBuilder("waddup fellas?");
                out.println("get only the \"waddup\" part: " + a.substring(0, a.indexOf(" ")));
                // out.println("append \"world\" to that substring: " + a.substring(0, a.indexOf(" ")).append(" world")); // does not compile because substring returns a String, not a StringBuilder
            }

            out.println("textbook example on StringBuilder's append");
            {
                StringBuilder a = new StringBuilder().append(1).append('c');
                a.append("-").append(true);
                out.println("a = " + a); // 1c-true
            }

            out.println("my own textbook example on StringBuilder's insert()");
            {
                StringBuilder a = new StringBuilder("hola loco");
                // StringBuilder insert(int offset, String str)
                a.insert(4, " mundo");
                out.println("a.insert(4, \" mundo\") = " + a);
            }

            out.println("textbook example on StringBuilder's insert()");
            {
                StringBuilder a = new StringBuilder("animals");
                out.println(a.insert(7, "-")); // animals-
                out.println(a.insert(0, "-")); // -animals-
                out.println(a.insert(4, "-")); // -ani-mals-
            }

            out.println("my own textbook example on StringBuilder's delete()");
            {
                StringBuilder a = new StringBuilder("-ani-mals-");
                // StringBuilder delete(int start, int end)
                // StringBuilder deleteCharAt(int index)
                out.println("a = " + a);
                out.println("a.deleteCharAt(0) = " + a.deleteCharAt(0));
                out.println("a.delete(3, 4) = " + a.delete(3, 4));
                out.println("a.delete(3, 4) = " + a.deleteCharAt(7));
            }

            out.println("textbook example on StringBuilder's delete() and deleteCharAt()");
            {
                StringBuilder a = new StringBuilder("abcdef");
                a.delete(1, 3); // a = adef
                // a.deleteCharAt(5); // throws StringIndexOutOfBoundsException
            }

            out.println("textbook example on StringBuilder's reverse()");
            {
                StringBuilder a = new StringBuilder("ABC");
                // signature is StringBuilder reverse()
                out.println("a = " + a);
                a.reverse();
                out.println("a = " + a);
                a.reverse();
            }

            out.println("textbook example on StringBuilder's toString()");
            {
                StringBuilder a = new StringBuilder("hola");
                out.println("a.toString() = " + a.toString());
            }

            out.println("equals does not do the same in String and StringBuilder");
            {
                StringBuilder a = new StringBuilder("ABC");
                StringBuilder b = new StringBuilder("ABC");
                out.println("a == b = " + (a == b)); // false
                out.println("a.equals(b) = " + a.equals(b)); // false
                out.println("a.toString() == b.toString() = " + (a.toString() == b.toString())); // false
                out.println("a.toString().equals(b.toString()) = " + a.toString().equals(b.toString())); // true
            }

            out.println("example on StringBuilder + ==");
            {
                StringBuilder one = new StringBuilder();
                StringBuilder two = new StringBuilder();
                StringBuilder three = one.append("a");
                out.println(one == two); // false
                out.println(one == three); // true
            }
            
            out.println("String + == (1)");
            {
                String x = "Hello World"; // pooled literal
                String y = "Hello World"; // pooled literal
                out.println("x == y = " + (x == y));
                out.println("x.equals(y) = " + x.equals(y));
            }

            out.println("String + == (2)");
            {
                String x = new String("Hello World");
                String y = "Hello World";
                out.println("x == y = " + (x == y));
            }

            out.println("String + == (3)");
            {
                String x = "Hello World";
                String y = " Hello World".trim();
                out.println(x.equals(y)); // true
            }
        }
    }

}
