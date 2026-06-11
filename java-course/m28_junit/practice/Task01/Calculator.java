package m28_junit.practice.task01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов, менять не нужно)
class Calculator {
    int add(int a, int b)      { return a + b; }
    int subtract(int a, int b) { return a - b; }
    int multiply(int a, int b) { return a * b; }
}
