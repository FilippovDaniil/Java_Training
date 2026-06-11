package m101_spring_test_basics.practice.task01;

/**
 * Задача 01 — Модуль 101: JUnit 5 — базовые проверки и @DisplayName
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Класс-под-тестом TaskPriority01 (готов ниже) считает приоритет. Напишите тесты:
 *     1) high_for_urgent(): @DisplayName("срочная → HIGH"); assertEquals("HIGH", calc(true, 1)).
 *     2) low_for_normal(): assertEquals("LOW", calc(false, 10)).
 *     3) throws_on_negative_days(): assertThrows(IllegalArgumentException.class, () -> calc(false, -1)).
 *
 * ЦЕЛЬ: освоить @Test, assertEquals (ожидаемое, фактическое), assertThrows, @DisplayName.
 *
 * ВАЖНО: в JUnit assertEquals(expected, actual) — первым идёт ОЖИДАЕМОЕ значение.
 *
 * ПОДСКАЗКА: статические импорты Assertions.* уже добавлены.
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// --- ТЕСТ (каркас) ---
class TaskPriorityTest01 {

    @Test
    // TODO: @DisplayName("срочная → HIGH")
    void high_for_urgent() {
        // TODO: assertEquals("HIGH", TaskPriority01.calc(true, 1));
    }

    @Test
    void low_for_normal() {
        // TODO: assertEquals("LOW", TaskPriority01.calc(false, 10));
    }

    @Test
    void throws_on_negative_days() {
        // TODO: assertThrows(IllegalArgumentException.class, () -> TaskPriority01.calc(false, -1));
    }
}
