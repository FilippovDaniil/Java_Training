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

    // TODO: параметризованные тесты с @ValueSource и @CsvSource

}
