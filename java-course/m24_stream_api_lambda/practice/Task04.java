package m24_stream_api_lambda.practice;

/**
 * Задача 04 — Модуль 24: map + collect
 *
 * ЗАДАНИЕ:
 *   Дан список имён в разном регистре. С помощью Stream:
 *     1) преобразуйте все имена в верхний регистр (map);
 *     2) соберите результат в новый список (collect);
 *     3) выведите его.
 *   Дополнительно: получите одну строку из имён через запятую
 *   (Collectors.joining).
 *
 * ПРИМЕР:
 *   ["anna", "boris", "viktor"]
 *   [ANNA, BORIS, VIKTOR]
 *   Через запятую: ANNA, BORIS, VIKTOR
 *
 * ПОДСКАЗКА:
 *   .map(String::toUpperCase).collect(Collectors.toList());
 *   .collect(Collectors.joining(", "));
 */
import java.util.List;
import java.util.stream.Collectors;

public class Task04 {
    public static void main(String[] args) {
        List<String> names = List.of("anna", "boris", "viktor");
        // Ваш код здесь
        List<String> new_names = names.stream()
                .map(String::toUpperCase)
                .toList();

        String zap = new_names.stream()
                .collect(Collectors.joining(", "));

        System.out.println(new_names);
        System.out.println(zap);
    }
}
