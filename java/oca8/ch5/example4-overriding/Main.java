import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>(Arrays.asList("A", "B"));
        try {
            System.out.println(new RacingCar(l).getDrivers());
        } catch(Exception e) {
            System.out.println("This statement is unreachable.");
        }
        System.out.println(new RacingCarNoEx(new ArrayList<String>()).getDrivers());
        try {
            System.out.println(new RacingCar(new ArrayList<String>()).getDrivers());
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

class Car {

    private List<String> drivers;

    public Car(List<String> drivers) {
        this.drivers = drivers;
    }

    public List<String> getDrivers() throws Exception {
        if(drivers.size() == 0)
            throw new Exception("not enough drivers");
        return drivers;
    }
}

class RacingCar extends Car {

    public RacingCar(ArrayList<String> l) {
        super(l);
    }

    public ArrayList<String> getDrivers() throws RuntimeException, Exception {
        try {
            return (ArrayList<String>)super.getDrivers();
        } catch(Exception e) {
            throw new RuntimeException("parent had exception");
        }
    }
}

class RacingCarNoEx extends Car {

    public RacingCarNoEx(ArrayList<String> l) {
        super(l);
    }

    public ArrayList<String> getDrivers() {
        try {
            return (ArrayList<String>)super.getDrivers();
        } catch(Exception e) {
            return null;
        }
    }

}
