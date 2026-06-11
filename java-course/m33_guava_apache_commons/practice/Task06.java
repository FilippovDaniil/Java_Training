package m33_guava_apache_commons.practice;

/**
 * Задача 06 — Модуль 33: Apache Commons (StringUtils, CollectionUtils)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.apache.commons:commons-lang3:3.14.0
 *   org.apache.commons:commons-collections4:4.4
 *
 * ЗАДАНИЕ:
 *   1. StringUtils: продемонстрируйте null-безопасность —
 *      isBlank(null), isBlank("  "), capitalize("привет"),
 *      reverse("abc"), defaultIfBlank("", "—").
 *   2. CollectionUtils: для двух списков выведите union (объединение),
 *      intersection (пересечение) и проверку isEmpty.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   isBlank(null): true
 *   capitalize: Привет
 *   union: [...]
 *   intersection: [...]
 *
 * ПОДСКАЗКА:
 *   StringUtils.isBlank(null) НЕ бросает NPE (в отличие от s.trim()).
 *   CollectionUtils.union(a, b); CollectionUtils.intersection(a, b);
 */
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4);
        List<Integer> b = List.of(3, 4, 5, 6);
        // Продемонстрируйте StringUtils и CollectionUtils
    }
}
