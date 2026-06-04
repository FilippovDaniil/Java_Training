/**
 * Задача 02 — Модуль 101: JUnit 5 lifecycle (@BeforeEach/@AfterEach) и @Nested
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (JUnit 5).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Класс TaskList02 (готов) — простой список задач. Напишите тесты с жизненным циклом:
 *     1) @BeforeEach init(): создавать НОВЫЙ TaskList02 перед каждым тестом (изоляция!).
 *     2) starts_empty(): assertEquals(0, list.size()).
 *     3) add_increases_size(): list.add("Кофе"); assertEquals(1, list.size()).
 *     4) @Nested класс WhenHasOneTask: @BeforeEach добавляет 1 задачу; внутри тест
 *        remove_makes_empty(): list.remove("Кофе"); assertEquals(0, list.size()).
 *
 * ЦЕЛЬ: понять, что @BeforeEach даёт КАЖДОМУ тесту свежее состояние; @Nested группирует
 *       тесты с общим контекстом.
 *
 * ВАЖНО: без @BeforeEach тесты «протекают» друг в друга — порядок начинает влиять на результат.
 *
 * ПОДСКАЗКА: поле list объявите на уровне класса, инициализируйте в @BeforeEach.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

// --- ТЕСТ (каркас) ---
class TaskListTest02 {

    TaskList02 list;

    @BeforeEach
    void init() {
        // TODO: list = new TaskList02();
    }

    @Test
    void starts_empty() {
        // TODO: assertEquals(0, list.size());
    }

    @Test
    void add_increases_size() {
        // TODO: list.add("Кофе"); assertEquals(1, list.size());
    }

    @Nested
    class WhenHasOneTask {
        @BeforeEach
        void addOne() {
            // TODO: list.add("Кофе");
        }

        @Test
        void remove_makes_empty() {
            // TODO: list.remove("Кофе"); assertEquals(0, list.size());
        }
    }
}
