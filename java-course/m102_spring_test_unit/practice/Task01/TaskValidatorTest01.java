package m102_spring_test_unit.practice.task01;

/**
 * Задача 01 — Модуль 102: чистый unit-тест логики (без моков, без контекста)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5 + AssertJ).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   TaskValidator01 (готов) — чистая логика без зависимостей. Протестируйте все ветки:
 *     1) valid_title_ok(): validate("Кофе") не бросает (assertThatCode(...).doesNotThrowAnyException()).
 *     2) blank_title_rejected(): assertThatThrownBy(() -> validate("  "))
 *          .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("пустой").
 *     3) too_long_rejected(): строка из 300 символов → IllegalArgumentException.
 *
 * ЦЕЛЬ: понять, что чистую логику тестируют БЕЗ моков и БЕЗ Spring — просто вызов метода.
 *
 * ВАЖНО: такой тест — самый быстрый и надёжный; выделяйте логику в классы без зависимостей.
 *
 * ПОДСКАЗКА: "Кофе".repeat(n) или "a".repeat(300) для длинной строки.
 */

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// --- ТЕСТ (каркас) ---
class TaskValidatorTest01 {

    @Test
    void valid_title_ok() {
        // TODO: assertThatCode(() -> TaskValidator01.validate("Кофе")).doesNotThrowAnyException();
    }

    @Test
    void blank_title_rejected() {
        // TODO: assertThatThrownBy(() -> TaskValidator01.validate("  "))
        //              .isInstanceOf(IllegalArgumentException.class)
        //              .hasMessageContaining("пустой");
    }

    @Test
    void too_long_rejected() {
        // TODO: assertThatThrownBy(() -> TaskValidator01.validate("a".repeat(300)))
        //              .isInstanceOf(IllegalArgumentException.class);
    }
}
