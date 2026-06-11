package m08_merge_sort.practice;

/**
 * Задача 06 — Тема 08: Merge sort с ОДНИМ вспомогательным массивом
 *
 * ЗАДАНИЕ:
 *   Перепишите merge sort так, чтобы временный массив выделялся ОДИН раз
 *   (а не при каждом слиянии). Это снижает количество аллокаций и ускоряет
 *   работу. Буфер передаётся в рекурсию и переиспользуется на всех уровнях.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2, 7, 4, 6]
 *   После: [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - ровно одна аллокация буфера (new int[n]) на весь алгоритм;
 *   - результат и сложность те же (O(n log n), стабильность);
 *   - merge использует общий буфер, а не создаёт свой.
 *
 * ПОДСКАЗКА:
 *   sort(a, buf, lo, hi); merge(a, buf, lo, mid, hi) копирует в buf и обратно в a.
 */

import java.util.Arrays;

public class Task06 {

    static void mergeSort(int[] a) {
        int[] buf = new int[a.length];       // единственная аллокация
        // TODO: запустить sort(a, buf, 0, a.length-1)
    }

    // TODO: sort(int[] a, int[] buf, int lo, int hi) и merge(int[] a, int[] buf, int lo, int mid, int hi)

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2, 7, 4, 6};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
