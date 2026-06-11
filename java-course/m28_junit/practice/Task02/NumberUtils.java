package m28_junit.practice.task02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Класс под тестом (готов)
class NumberUtils {
    static boolean isPositive(int n) { return n > 0; }
    static int abs(int n) { return n < 0 ? -n : n; }
    static int[] sortedCopy(int[] arr) {
        int[] copy = arr.clone();
        java.util.Arrays.sort(copy);
        return copy;
    }
}
