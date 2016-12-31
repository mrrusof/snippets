import static java.lang.System.out;

public class WrapperClasses {
    public static void main(String[] args) {
        {
            out.println("Convert String to primitive (1)");
            int i = Integer.parseInt("123");
            Integer j = Integer.parseInt("123");
            out.println(i);
            out.println(j);
        }
        {
            out.println("Convert String to wrapper class (1)");
            Integer i = Integer.valueOf("123");
            int j = Integer.valueOf("123"); // does not compile
            out.println(i);
            out.println(j);
        }
        {
            out.println("Convert String to primitive (2)");
            boolean b = Boolean.parseBoolean("true");
            byte by = Byte.parseByte("1");
            short s = Short.parseShort("1");
            int i = Integer.parseInt("1");
            long l = Long.parseLong("1");
            Float f = Float.parseFloat("1.2");
            Double d = Double.parseDouble("1.2");
        }
        {
            out.println("Convert String to wrapper class (2)");
            Boolean b = Boolean.valueOf("true");
            Byte by = Byte.valueOf("1");
            Short s = Short.valueOf("1");
            Integer i = Integer.valueOf("1");
            Long l = Long.valueOf("1");
            Float f = Float.valueOf("1.2");
            Double d = Double.valueOf("1.2");
        }
    }
}
