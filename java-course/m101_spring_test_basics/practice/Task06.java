package m101_spring_test_basics.practice;

/**
 * Задача 06 — Модуль 101: JSONassert — сравнение JSON-строк
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JSONassert).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Сравните JSON-строки в разных режимах строгости:
 *     1) lenient_ignores_order_and_extra():
 *          expected = {"id":1,"title":"Кофе"};
 *          actual   = {"title":"Кофе","id":1,"extra":true};
 *          JSONAssert.assertEquals(expected, actual, false);  // LENIENT → проходит
 *     2) strict_fails_on_extra(): тот же actual со STRICT (true) НЕ должен совпасть —
 *          оберните в assertThrows(AssertionError.class, () -> JSONAssert.assertEquals(expected, actual, true)).
 *
 * ЦЕЛЬ: понять разницу LENIENT (false) и STRICT (true): порядок и лишние поля.
 *
 * ТАБЛИЦА:
 *   false (LENIENT) — порядок не важен, лишние поля в actual допустимы;
 *   true  (STRICT)  — точное соответствие набора и структуры.
 *
 * ПОДСКАЗКА: JSONAssert.assertEquals бросает AssertionError при несовпадении.
 */
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertThrows;

// --- ТЕСТ (каркас) ---
class JsonAssertTest06 {

    private static final String EXPECTED = "{\"id\":1,\"title\":\"Кофе\"}";
    private static final String ACTUAL   = "{\"title\":\"Кофе\",\"id\":1,\"extra\":true}";

    @Test
    void lenient_ignores_order_and_extra() throws Exception {
        // TODO: JSONAssert.assertEquals(EXPECTED, ACTUAL, false);   // false = LENIENT
    }

    @Test
    void strict_fails_on_extra() {
        // TODO: assertThrows(AssertionError.class,
        // TODO:     () -> JSONAssert.assertEquals(EXPECTED, ACTUAL, true));   // true = STRICT
    }
}
