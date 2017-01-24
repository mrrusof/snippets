import static java.lang.System.out;

public class Lion extends Animal {
    public void roar() {
        // out.println("The " + age + " year old lion says: Roar!"); // age has private access in Animal
        out.println("The " + getAge() + " year old lion says: Roar!");
    }
}
