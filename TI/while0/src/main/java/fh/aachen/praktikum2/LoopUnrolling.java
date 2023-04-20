package fh.aachen.praktikum2;

public class LoopUnrolling {
    public static final String[] STRINGS_TO_PRINT =
            {"A", "B", "C", "D"};
    public static void main(final String[] args) {
        for (String value : STRINGS_TO_PRINT) {
            System.out.println("String: " + value);
        }
        fullUnrolled()
    }
    public static void fullUnrolled() {
        System.out.println("String: A");
        System.out.println("String: B");
        System.out.println("String: C");
        System.out.println("String: D");
    }
}
