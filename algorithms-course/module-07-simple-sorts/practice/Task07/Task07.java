/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 07: выбор сортировки по размеру
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher AdaptiveSorter (файл AdaptiveSorter.java),
 *   который выбирает алгоритм по размеру: для n < 50 — insertion sort,
 *   иначе — O(n log n) (заглушка под merge sort из темы 08). Покажите выбор
 *   для нескольких размеров и корректную сортировку небольшого массива.
 *
 * ПРИМЕР / ВЫВОД:
 *   chooseAlgorithm(10)   -> insertion
 *   chooseAlgorithm(1000) -> mergeSort (тема 08)
 *   sort([5,3,8,1,2]) -> [1, 2, 3, 5, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - порог THRESHOLD задаётся в классе (например 50);
 *   - малый массив реально сортируется вставками;
 *   - предусмотрите место для подключения merge sort (тема 08) — комментарий TODO.
 *
 * ПОДСКАЗКА:
 *   Это «крючок» для следующей темы: в 08 здесь появится настоящий O(n log n).
 */

import java.util.Arrays;

public class Task07 {
    public static void main(String[] args) {
        AdaptiveSorter sorter = new AdaptiveSorter();
        System.out.println("chooseAlgorithm(10)   -> " + sorter.chooseAlgorithm(10));
        System.out.println("chooseAlgorithm(1000) -> " + sorter.chooseAlgorithm(1000));
        int[] a = {5, 3, 8, 1, 2};
        sorter.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
