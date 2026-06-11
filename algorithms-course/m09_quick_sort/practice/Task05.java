package m09_quick_sort.practice;

/**
 * Задача 05 — Тема 09: Гибрид — переключение на insertion sort для малых подмассивов
 *
 * ЗАДАНИЕ:
 *   Реализуйте гибридный quicksort: если размер подмассива меньше порога
 *   (например 10) — сортируйте его insertion sort'ом вместо рекурсивного
 *   разбиения. На маленьких данных insertion быстрее (меньше накладных расходов).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [9, 3, 7, 1, 8, 2, 6, 4, 5, 0, ...]
 *   После: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, ...]
 *
 * ТРЕБОВАНИЯ:
 *   - порог CUTOFF (например 10);
 *   - если hi - lo + 1 <= CUTOFF -> insertionSort(a, lo, hi) и return;
 *   - иначе обычное разбиение + рекурсия;
 *   - результат отсортирован.
 *
 * ПОДСКАЗКА:
 *   insertionSort должен сортировать ПОДОТРЕЗОК a[lo..hi], а не весь массив.
 */

import java.util.Arrays;

public class Task05 {

    static final int CUTOFF = 10;

    static void quicksort(int[] a, int lo, int hi) {
        // TODO: если отрезок мал — insertionSort и return; иначе partition + рекурсия
    }

    static void insertionSort(int[] a, int lo, int hi) {
        // TODO: сортировка вставками для подотрезка [lo, hi]
    }

    static int partition(int[] a, int lo, int hi) {
        // TODO: разбиение Ломуто
        return lo;
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 1, 8, 2, 6, 4, 5, 0, 15, 11, 13, 12, 14};
        quicksort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
