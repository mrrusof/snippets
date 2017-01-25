package pkg1;

public class Car {

    private static int count = 0;
    private String brand;
    private int id;

    public Car(String brand) {
        this.brand = brand;
        id = count++;
    }

    public String getBrand() {
        return brand;
    }

    protected int getId() {
        return id;
    }
}
