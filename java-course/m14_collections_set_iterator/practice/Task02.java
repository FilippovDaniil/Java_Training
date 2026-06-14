package m14_collections_set_iterator.practice;

/**
 * Задача 02 — Модуль 14: Удаление дубликатов из списка
 *
 * ЗАДАНИЕ:
 *   Дан список чисел с повторами. Получите список уникальных значений,
 *   используя Set. Выведите количество до и после.
 *
 * ПРИМЕР:
 *   Исходный: [1, 2, 2, 3, 3, 3, 4, 1]  (8 элементов)
 *   Уникальные: [1, 2, 3, 4]            (4 элемента)
 *
 * ПОДСКАЗКА:
 *   Set<Integer> unique = new HashSet<>(list);
 *   В конструктор HashSet можно передать готовую коллекцию.
 */
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Task02 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 4, 2, 3, 3, 3, 4, 1);
        Set<Integer> new_numbers = new HashSet<>(numbers);

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();

        for (Integer newNumber : new_numbers) {
            System.out.print(newNumber + " ");
        }
        // Ваш код здесь
    }
}
