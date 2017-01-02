import static java.lang.System.out;
import java.time.*;
import java.time.format.*;

public class DatesAndTime {
    public static void main(String[] a) {
        {
            out.println("LocalDate (1)");
            LocalDate d = LocalDate.now();
            out.println(d);
        }
        {
            out.println("LocalTime (1)");
            LocalTime t = LocalTime.now();
            out.println(t);
        }
        {
            out.println("LocalDateTime (1)");
            LocalDateTime dt = LocalDateTime.now();
            out.println(dt);
        }
        {
            out.println("LocalDate (2)");
            // public static LocalDate of(int year, Month month, int dayOfMonth)
            LocalDate d1 = LocalDate.of(2016, Month.DECEMBER, 30);
            out.println(d1);
            // public static LocalDate of(int year, int month, int dayOfMonth)
            LocalDate d2 = LocalDate.of(2016, 12, 30);
            out.println(d2);
        }
        {
            out.println("LocalTime (1)");
            // public static LocalTime of(int hour, int minute)
            LocalTime t = LocalTime.of(7,40);
            out.println(t);
            // public static LocalTime of(int hour, int minute, int second)
            t = LocalTime.of(7,40,30);
            out.println(t);
            // public static LocalTime of(int hour, int minute, int second, int nanos)
            t = LocalTime.of(7,40,30,100);
            out.println(t);
        }
        {
            out.println("LocalDateTime (2)");
            LocalDateTime dt = LocalDateTime.of(2016, 12, 30, 7, 40);
            out.println(dt);
            dt = LocalDateTime.of(2016, 12, 30, 7, 40, 30);
            out.println(dt);
            dt = LocalDateTime.of(2016, 12, 30, 7, 40, 30, 100);
            out.println(dt);
        }
        {
            out.println("LocalDateTime (3)");
            LocalDate d = LocalDate.of(2016,12,30);
            LocalTime t = LocalTime.of(7,40);
            LocalDateTime dt = LocalDateTime.of(d, t);
            out.println(dt);
        }
        {
            out.println("LocalDate (3)");
            //LocalDate d = LocalDate.of(2016, 12, 32); // runtime exception
        }
        {
            out.println("Manipulating Dates and Times (1)");
            LocalDate date = LocalDate.of(2016, Month.DECEMBER, 30);
            out.println(date);
            date = date.plusDays(2);
            out.println(date);
            date = date.plusWeeks(1);
            out.println(date);
            date = date.plusMonths(2);
            out.println(date);
            date = date.plusYears(1);
            out.println(date);
            date.plusDays(10);
            out.println(date);
            // date.plusMinutes(1); // does not compile
            LocalTime time = LocalTime.of(7,40);
            out.println(time);
            time = time.plusHours(1);
            out.println(time);
            time = time.plusMinutes(30);
            out.println(time);
            time = time.plusSeconds(30);
            out.println(time);
            time = time.plusNanos(100);
            out.println(time);
            LocalDateTime dt = LocalDateTime.of(date, time);
            out.println(dt);
            dt = dt.plusSeconds(30);
            out.println(dt);
            dt = dt.plusDays(1);
            out.println(dt);
        }
        {
            out.println("Zoo (1)");
            LocalDate start = LocalDate.of(2017, Month.JANUARY, 01);
            out.println(start);
            LocalDate end = LocalDate.of(2017, Month.MARCH, 31);
            out.println(end);
            executeTask(start, end);
        }
        {
            out.println("Zoo (2)");
            LocalDate start = LocalDate.of(2017, 1, 1);
            LocalDate end = LocalDate.of(2017, 3, 31);
            Period period = Period.ofMonths(1);
            executeTaskV2(start, end, period);
        }
        {
            out.println("Period (1)");
            Period p = Period.ofMonths(1).ofYears(1);
            out.println(p);
            p = Period.of(1, 1, 0);
            out.println(p);
        }
        {
            out.println("Period (2)");
            LocalDate date = LocalDate.of(2015, 1, 20);
            LocalTime time = LocalTime.of(6, 15);
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            Period period = Period.ofMonths(1);
            out.println(date.plus(period));
            out.println(dateTime.plus(period)); // compiles!
            //out.println(time.plus(period)); // UnsupportedTemporalTypeException

            LocalDate d = LocalDate.of(2016,12,30);
            Period p = Period.ofDays(1);
            out.println(d.plus(p));
            LocalDateTime dt = LocalDateTime.of(2016, Month.DECEMBER, 30, 0, 0);
            out.println(dt);
            out.println(dt.plus(p));
            LocalTime t = LocalTime.of(0,0);
            out.println(t);
            //out.println(t.plus(d)); // does not compile
        }
        {
            out.println("Data from LocalDate (1)");
            LocalDate d = LocalDate.of(2016, Month.DECEMBER, 30);
            out.println(d.getYear());
            out.println(d.getMonth());
            out.println(d.getDayOfMonth());
            out.println(d.getDayOfWeek());
            out.println(d.getDayOfYear());
        }
        {
            out.println("java.time.format.DateTimeFormatter (1)");
            LocalDate d = LocalDate.of(2016,12,30);
            LocalTime t = LocalTime.of(7,40);
            LocalDateTime dt = LocalDateTime.of(d, t);
            out.println(d.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
            out.println(t.format(java.time.format.DateTimeFormatter.ISO_LOCAL_TIME));
            out.println(dt.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        {
            out.println("java.time.format.DateTimeFormatter (2)");
            java.time.format.DateTimeFormatter shortDateTime = java.time.format.DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT);
            LocalDate d = LocalDate.of(2016,12,30);
            LocalTime t = LocalTime.of(7,40);
            LocalDateTime dt = LocalDateTime.of(d, t);
            out.println(dt.format(shortDateTime));
            out.println(shortDateTime.format(dt));
            out.println(d.format(shortDateTime));
            out.println(shortDateTime.format(d));
            //out.println(t.format(shortDateTime)); // runtime exception
        }
        {
            out.println("java.time.format.DateTimeFormatter (3)");
            java.time.format.DateTimeFormatter f = java.time.format.DateTimeFormatter.ofPattern("hh:mm");
            LocalTime t = LocalTime.of(7,40);
            System.out.println(f.format(t));
            System.out.println(t.format(f));
            LocalDate d = LocalDate.of(2016,12,30);
            // System.out.println(d.format(f)); // runtime exception
            // System.out.println(f.format(d)); // runtime exception
        }
        {
            out.println("java.time.format.DateTimeFormatter (4)");
            java.time.format.DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
            LocalDate d = LocalDate.now();
            System.out.println("d = " + d.format(f));
            System.out.println("d = " + f.format(d));
        }
        {
            out.println("java.time.format.DateTimeFormatter (5)");
            java.time.format.DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            LocalTime t = LocalTime.now();
            out.println("t = " + t.format(f));
            out.println("t = " + f.format(t));
        }
        {
            out.println("java.time.format.DateTimeFormatter (6)");
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            LocalDateTime dt = LocalDateTime.now();
            out.println("dt = " + dt.format(f));
            out.println("dt = " + f.format(dt));
        }
        {
            out.println("java.time.format.DateTimeFormatter (7)");
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
            LocalDateTime dt = LocalDateTime.now();
            out.println("dt = " + dt.format(f));
        }
        {
            out.println("java.time.format.DateTimeFormatter (8)");
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
            LocalDateTime dt = LocalDateTime.of(2017,1,1,21,58);
            out.println("dt = " + dt.format(f));
            out.println("dt = " + f.format(dt));
        }
        {
            out.println("java.time.format.DateTimeFormatter (9)");
            DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
            LocalDateTime dt = LocalDateTime.of(2017,1,1,21,58);
            out.println("dt = " + dt.format(f));
        }
        {
            out.println("java.time.LocalDate (1)");
            LocalDate d = LocalDate.of(2016,12,31);
            Period p = Period.ofYears(1).ofDays(1);
            d = d.plus(p);
            System.out.println(d);
        }
        {
            out.println("java.time.LocalDate (2)");
            LocalDate d = LocalDate.of(2016,12,31);
            Period p = Period.ofDays(1).ofYears(1);
            d = d.plus(p);
            System.out.println(d);
        }
        {
            out.println("Parse date/time (1)");
            java.time.format.DateTimeFormatter f = java.time.format.DateTimeFormatter.ofPattern("MM dd yyyy");
            LocalDate date = LocalDate.parse("12 30 2016", f);
            out.println(date);
            LocalTime time = LocalTime.parse("11:22");
            out.println(time);
        }
        {
            out.println("Parse date/time (2)");
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate d = LocalDate.parse("2016/12/31", f);
            out.println(d.format(f));
            f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime dt = LocalDateTime.parse("2016/12/31 04:59", f);
            out.println(dt.format(f));
        }
    }

    private static void executeTask(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while(upTo.isBefore(end)) {
            System.out.println("take one step in task: " + upTo);
            upTo = upTo.plusMonths(1);
        }
    }

    private static void executeTaskV2(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while(upTo.isBefore(end)) {
            out.println("take one step in task: " + upTo);
            upTo = upTo.plus(period);
        }
    }
}
