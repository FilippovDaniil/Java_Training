package m28_junit.practice.task01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов, менять не нужно)
public class Calculator {

    public static int add(int a, int b)      { return a + b; }
    public static int subtract(int a, int b) { return a - b; }
    public static int multiply(int a, int b) { return a * b; }
}
