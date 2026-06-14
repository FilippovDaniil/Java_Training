package m13_lists_generics.practice;

/**
 * Задача 04 — Модуль 13: Фильтрация в новый список
 *
 * ЗАДАНИЕ:
 *   Дан список чисел. Создайте НОВЫЙ список, в который попадут только
 *   чётные числа из исходного. Выведите оба списка.
 *
 * ПРИМЕР:
 *   Исходный: [1, 2, 3, 4, 5, 6, 7, 8]
 *   Чётные:   [2, 4, 6, 8]
 *
 * ПОДСКАЗКА:
 *   Заведите второй List<Integer>, в цикле добавляйте подходящие числа.
 */
import java.util.ArrayList;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 32; i++) numbers.add(i);
        // Ваш код здесь
        List<Integer> new_numbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0){
                new_numbers.add(number);
            }
        }

        System.out.println(new_numbers);
    }
}
