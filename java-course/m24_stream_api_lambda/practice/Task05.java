package m24_stream_api_lambda.practice;

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
import java.util.Optional;
import java.util.OptionalDouble;

public class Task05 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 12, 8, 20, 3, 15);
        // Ваш код здесь
        int count_more_than_ten = (int) numbers.stream().filter(x -> x > 10).count();
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        OptionalDouble avgList = numbers.stream().mapToInt(Integer::intValue).average();
        Optional max = numbers.stream().max(Integer::compareTo);

        System.out.println("Больше 10: " + count_more_than_ten);
        System.out.println("Сумма: " + sum);
        System.out.println("Среднее: " + avgList.orElse(0.0));
        System.out.println("Max: " + max.get());
    }
}
