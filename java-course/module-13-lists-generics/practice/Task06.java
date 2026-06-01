/**
 * Задача 06 — Модуль 13: Сортировка и Collections
 *
 * ЗАДАНИЕ:
 *   Дан список чисел в произвольном порядке. С помощью класса
 *   Collections:
 *     - отсортируйте список по возрастанию;
 *     - выведите минимум и максимум;
 *     - разверните список (по убыванию).
 *
 * ПРИМЕР:
 *   Исходный:        [5, 2, 8, 1, 9, 3]
 *   Отсортированный: [1, 2, 3, 5, 8, 9]
 *   Минимум: 1, Максимум: 9
 *   По убыванию:     [9, 8, 5, 3, 2, 1]
 *
 * ПОДСКАЗКА:
 *   Collections.sort(list), Collections.min(list),
 *   Collections.max(list), Collections.reverse(list).
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(5, 2, 8, 1, 9, 3));
        // Ваш код здесь
    }
}
