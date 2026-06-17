package m28_junit.practice.task02;

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

import m28_junit.practice.task01.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task02 {

    // TODO: напишите тесты с assertTrue, assertFalse, assertEquals,
    //       assertArrayEquals, assertNull/assertNotNull

    @BeforeEach
    void setUp() {            // выполняется ПЕРЕД каждым тестом
        System.out.println("Test is running");
        System.out.println("-----------------------------");
    }

    @AfterEach
    void setEnd() {            // выполняется ПЕРЕД каждым тестом
        System.out.println();
    }

    @Test
    @DisplayName("Проверка на положительность")
    void testAdd() {
        // Ваш код здесь
        assertTrue(NumberUtils.isPositive(5));
    }

    @Test
    @DisplayName("Приведение к положительному")
    void testSubstract() {
        // Ваш код здесь
        assertEquals(5,NumberUtils.abs(-5));
    }

    @Test
    @DisplayName("Сравнение 2 массивов")
    void testMultipy() {
        // Ваш код здесь
        assertArrayEquals(new int[]{1,2,3}, NumberUtils.sortedCopy(new int[]{3,2,1}));
    }

}
