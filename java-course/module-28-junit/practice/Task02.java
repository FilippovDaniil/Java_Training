/**
 * Задача 02 — Модуль 28: Разные виды проверок
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс NumberUtils реализован. Напишите тесты, применив РАЗНЫЕ
 *   assertions:
 *     1) assertTrue  — isPositive(5) истинно;
 *     2) assertFalse — isPositive(-3) ложно;
 *     3) assertEquals — abs(-7) равно 7;
 *     4) assertArrayEquals — sortedCopy({3,1,2}) равно {1,2,3};
 *     5) assertNull / assertNotNull — придумайте подходящий случай.
 *
 * ПОДСКАЗКА:
 *   assertTrue(NumberUtils.isPositive(5));
 *   assertArrayEquals(new int[]{1,2,3}, NumberUtils.sortedCopy(...));
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task02 {

    // TODO: напишите тесты с assertTrue, assertFalse, assertEquals,
    //       assertArrayEquals, assertNull/assertNotNull

}

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
