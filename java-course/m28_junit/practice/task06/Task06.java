package m28_junit.practice.task06;

/**
 * Задача 06 — Модуль 28: Параметризованные тесты
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс MathUtils реализован. Напишите ПАРАМЕТРИЗОВАННЫЕ тесты:
 *     1) @ParameterizedTest + @ValueSource(ints = {2,4,6,8,100}) —
 *        проверьте, что isEven возвращает true для всех этих чисел;
 *     2) @ParameterizedTest + @ValueSource(ints = {1,3,5,7,99}) —
 *        проверьте, что isEven возвращает false;
 *     3) @ParameterizedTest + @CsvSource({"2,3,5", "10,5,15", "-1,1,0"}) —
 *        проверьте, что add(a, b) == expected.
 *
 * ПОДСКАЗКА:
 *   @ParameterizedTest
 *   @ValueSource(ints = {2, 4, 6})
 *   void testEven(int n) { assertTrue(MathUtils.isEven(n)); }
 *
 *   @ParameterizedTest
 *   @CsvSource({"2,3,5"})
 *   void testAdd(int a, int b, int expected) { ... }
 */

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class Task06 {

    // Тест 1: Проверка четных чисел - isEven возвращает true
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    void testIsEvenForEvenNumbers(int number) {
        assertTrue(MathUtils.isEven(number),
                number + " should be even");
    }

    // Тест 2: Проверка нечетных чисел - isEven возвращает false
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    void testIsEvenForOddNumbers(int number) {
        assertFalse(MathUtils.isEven(number),
                number + " should be odd");
    }

    // Тест 3: Проверка сложения с использованием CsvSource
    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 5, 15",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 50, 150",
            "-5, -3, -8"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, MathUtils.add(a, b),
                a + " + " + b + " should equal " + expected);
    }

    // Дополнительный тест: Проверка isEven с граничными значениями
    @ParameterizedTest
    @ValueSource(ints = {0, -2, -4, -6, -100})
    void testIsEvenForEvenNumbersIncludingZeroAndNegative(int number) {
        assertTrue(MathUtils.isEven(number),
                number + " should be even (including zero and negative numbers)");
    }

    // Дополнительный тест: Проверка isEven с отрицательными нечетными числами
    @ParameterizedTest
    @ValueSource(ints = {-1, -3, -5, -7, -99})
    void testIsEvenForNegativeOddNumbers(int number) {
        assertFalse(MathUtils.isEven(number),
                number + " should be odd (negative odd numbers)");
    }

    // Дополнительный тест: Сложение с большими числами
    @ParameterizedTest
    @CsvSource({
            "1000, 2000, 3000",
            "-1000, 500, -500" // Переполнение int
    })
    void testAddWithLargeNumbers(int a, int b, int expected) {
        assertEquals(expected, MathUtils.add(a, b),
                a + " + " + b + " should equal " + expected);
    }
}
