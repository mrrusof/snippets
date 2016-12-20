import java.util.Random;
// This explicit import has precedence over `import java.sql.*;`.
//import java.util.Date;
//import java.sql.*;

public class ImportExample {
    public static void main(String[] args) {
        java.util.Date d = new java.util.Date();
        Random r = new Random();
        System.out.println(r.nextInt(10));
    }
}
