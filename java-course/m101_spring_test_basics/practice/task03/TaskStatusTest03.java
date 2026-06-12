package m101_spring_test_basics.practice.task03;

/**
 * Задача 03 — Модуль 101: параметризованные тесты (@ParameterizedTest)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (junit-jupiter-params).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Класс TaskStatus03 (готов) определяет, можно ли редактировать задачу в данном статусе.
 *   Напишите параметризованные тесты — один метод проверяет много случаев:
 *     1) @ParameterizedTest + @ValueSource(strings = {"NEW","IN_PROGRESS"}) → canEdit(status) == true.
 *     2) @ParameterizedTest + @CsvSource({"NEW,true", "DONE,false", "CANCELLED,false"}):
 *          assertEquals(expected, TaskStatus03.canEdit(status)).
 *     3) @ParameterizedTest + @MethodSource("blankTitles") → title.isBlank() == true;
 *        статический метод blankTitles() возвращает Stream.of("", "  ", "\t").
 *
 * ЦЕЛЬ: заменить копипасту похожих тестов одним параметризованным методом.
 *
 * ВАЖНО: @MethodSource ссылается на СТАТИЧЕСКИЙ метод-поставщик данных по имени.
 *
 * ПОДСКАЗКА: @ParameterizedTest вместо @Test; источник данных — отдельной аннотацией.
 */

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// --- ТЕСТ (каркас) ---
class TaskStatusTest03 {

    @ParameterizedTest
    @ValueSource(strings = {"NEW", "IN_PROGRESS"})
    void editable_statuses(String status) {
        // TODO: assertTrue(TaskStatus03.canEdit(status));
    }

    @ParameterizedTest
    @CsvSource({"NEW,true", "DONE,false", "CANCELLED,false"})
    void canEdit_matrix(String status, boolean expected) {
        // TODO: assertEquals(expected, TaskStatus03.canEdit(status));
    }

    @ParameterizedTest
    @MethodSource("blankTitles")
    void detects_blank(String title) {
        // TODO: assertTrue(title.isBlank());
    }

    static Stream<String> blankTitles() {
        // TODO: return Stream.of("", "  ", "\t");
        return Stream.of();
    }
}
