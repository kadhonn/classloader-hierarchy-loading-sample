package at.abl;

public class MyClass {
    private static int counter = 0;

    public static void doIt() {
        Introspection.call();
        counter++;
        System.out.println("doing it! new counter: " + counter);
    }
}
