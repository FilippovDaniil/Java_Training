package m08_merge_sort.practice;

/**
 * Задача 01 — Тема 08: Рекурсивная сортировка слиянием для int[]
 *
 * ЗАДАНИЕ:
 *   Реализуйте классический рекурсивный merge sort: делите массив пополам,
 *   рекурсивно сортируйте половины, затем сливайте их.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2, 7, 4, 6]
 *   После: [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - O(n log n) время, O(n) доп. память;
 *   - стабильность: при слиянии равных элементов сначала берите из левой части (<=);
 *   - mid = lo + (hi - lo) / 2; базовый случай lo >= hi.
 *
 * ПОДСКАЗКА:
 *   merge(a, lo, mid, hi): слить две отсортированные части через временный массив
 *   и скопировать результат обратно в a[lo..hi].
 */

import java.util.Arrays;

public class Task01 {

    static void mergeSort(int[] a, int lo, int hi) {
        // TODO: базовый случай; рекурсия по половинам; merge
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        // TODO: два указателя, временный массив, копирование обратно
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2, 7, 4, 6};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
