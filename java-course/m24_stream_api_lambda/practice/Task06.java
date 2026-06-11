package m24_stream_api_lambda.practice;

/**
 * Задача 06 — Модуль 24: sorted, distinct, limit + ссылки на методы
 *
 * ЗАДАНИЕ:
 *   Дан список чисел с дубликатами и в произвольном порядке.
 *   С помощью одного конвейера Stream:
 *     1) уберите дубликаты (distinct);
 *     2) отсортируйте по убыванию (sorted с компаратором);
 *     3) возьмите первые 3 (limit);
 *     4) выведите результат (используйте ссылку на метод).
 *
 * ПРИМЕР:
 *   [5, 2, 8, 5, 1, 8, 9, 3, 9]
 *   Топ-3 уникальных: 9, 8, 5
 *
 * ПОДСКАЗКА:
 *   .distinct().sorted(Comparator.reverseOrder()).limit(3)
 *            .forEach(System.out::println);
 */
import java.util.Comparator;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 2, 8, 5, 1, 8, 9, 3, 9);
        // Ваш код здесь
    }
}
