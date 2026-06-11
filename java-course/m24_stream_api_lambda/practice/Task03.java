package m24_stream_api_lambda.practice;

/**
 * Задача 03 — Модуль 24: filter + forEach
 *
 * ЗАДАНИЕ:
 *   Дан список слов. Используя Stream, выведите только те слова,
 *   длина которых больше 4 символов.
 *
 * ПРИМЕР:
 *   ["кот", "собака", "рыба", "черепаха", "ёж"]
 *   Длинные слова:
 *   собака
 *   черепаха
 *
 * ПОДСКАЗКА:
 *   list.stream().filter(w -> w.length() > 4).forEach(System.out::println);
 */
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        List<String> words = List.of("кот", "собака", "рыба", "черепаха", "ёж");
        // Ваш код здесь
    }
}
