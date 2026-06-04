/**
 * Задача 05 — Модуль 101: Hamcrest — матчеры
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-test (Hamcrest).
 *
 * Это ТЕСТ-КЛАСС (без main). Запуск в IDE (▶) или ./gradlew test.
 *
 * ЗАДАНИЕ:
 *   Используя Hamcrest (assertThat(value, matcher)), напишите проверки:
 *     1) value_is(): assertThat(2 + 3, is(5)).
 *     2) list_has_item(): assertThat(List.of("a","b","c"), hasItem("b")).
 *     3) list_size(): assertThat(List.of("a","b","c"), hasSize(3)).
 *     4) combined(): assertThat("Alice", allOf(startsWith("Al"), endsWith("ce"))).
 *   Статические импорты Hamcrest добавлены.
 *
 * ЦЕЛЬ: познакомиться с матчерами — этот стиль понадобится в jsonPath(..., matcher) (модуль 104).
 *
 * ВАЖНО: здесь assertThat из org.hamcrest.MatcherAssert (НЕ AssertJ). Не смешивать импорты.
 *
 * ПОДСКАЗКА: матчеры комбинируются (allOf/anyOf) и читаются как «значение есть/имеет ...».
 */
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

// --- ТЕСТ (каркас) ---
class HamcrestTest05 {

    @Test
    void value_is() {
        // TODO: assertThat(2 + 3, is(5));
    }

    @Test
    void list_has_item() {
        // TODO: assertThat(List.of("a", "b", "c"), hasItem("b"));
    }

    @Test
    void list_size() {
        // TODO: assertThat(List.of("a", "b", "c"), hasSize(3));
    }

    @Test
    void combined() {
        // TODO: assertThat("Alice", allOf(startsWith("Al"), endsWith("ce")));
    }
}
