package m08_merge_sort.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 08: merge sort как основной метод
 *
 * ЗАДАНИЕ:
 *   Подключите merge sort в Data-Cruncher (файл MergeSorter.java) как основной
 *   алгоритм для больших данных. Это «достройка» AdaptiveSorter из темы 07:
 *   для n < порога — insertion sort, иначе — merge sort. Покажите сортировку
 *   большого массива и проверьте корректность (отсортирован ли результат).
 *
 * ПРИМЕР / ВЫВОД:
 *   Отсортировано 200000 элементов merge sort за t=... ms
 *   Проверка отсортированности: OK
 *
 * ТРЕБОВАНИЯ:
 *   - MergeSorter.sort(int[]) — стабильный O(n log n);
 *   - метод isSorted(int[]) для проверки результата;
 *   - продемонстрируйте на большом массиве (десятки/сотни тысяч).
 *
 * ПОДСКАЗКА:
 *   Используйте версию с одним буфером (задача 06) — она эффективнее.
 */

public class Task07 {
    public static void main(String[] args) {
        int n = 200_000;
        int[] data = new int[n];
        // TODO: заполнить случайными числами, отсортировать MergeSorter'ом с замером,
        //       проверить isSorted
        MergeSorter sorter = new MergeSorter();
        // ...
    }
}
