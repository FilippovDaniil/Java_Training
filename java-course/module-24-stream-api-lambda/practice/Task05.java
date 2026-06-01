/**
 * Задача 05 — Модуль 24: Агрегация (count, sum, average, reduce)
 *
 * ЗАДАНИЕ:
 *   Дан список чисел. С помощью Stream вычислите:
 *     - количество чисел больше 10 (filter + count);
 *     - сумму всех чисел (mapToInt + sum или reduce);
 *     - среднее арифметическое (average);
 *     - максимум (max).
 *
 * ПРИМЕР (для [5, 12, 8, 20, 3, 15]):
 *   Больше 10: 3
 *   Сумма: 63
 *   Среднее: 10.5
 *   Максимум: 20
 *
 * ПОДСКАЗКА:
 *   .filter(...).count();  .mapToInt(Integer::intValue).sum();
 *   .average().orElse(0);  .max(Integer::compareTo).
 */
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 12, 8, 20, 3, 15);
        // Ваш код здесь
    }
}
