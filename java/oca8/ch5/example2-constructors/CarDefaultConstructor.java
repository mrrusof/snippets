public class CarDefaultConstructor {
    private String brand;
    public CarDefaultConstructor(String brand) {
        this.brand = brand;
    }
    public CarDefaultConstructor() {
        this("Opel");
    }
    public String getBrand() {
        return brand;
    }
}
