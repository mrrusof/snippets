public class Zoo {

    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);

        Animal a = new Animal();
        a.setName("Python");
        System.out.println(a.getName());
    }

}
