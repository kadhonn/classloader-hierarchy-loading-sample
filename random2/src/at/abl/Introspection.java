package at.abl;

public class Introspection {

    private static int counter = 0;

    public static void call() {
        counter++;
        System.out.println("introspection! new counter: " + counter);
    }
}
