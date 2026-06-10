/**
 * Задача 01 — Тема 09: Quicksort с разбиением Ломуто
 *
 * ЗАДАНИЕ:
 *   Реализуйте быструю сортировку с разбиением Ломуто (pivot — последний элемент).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [3, 7, 2, 8, 5, 1, 6, 4]
 *   После: [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - in-place; partition возвращает финальный индекс pivot;
 *   - рекурсия на [lo, p-1] и [p+1, hi];
 *   - базовый случай lo >= hi.
 *
 * ПОДСКАЗКА:
 *   partition: pivot=a[hi]; i=lo; for j in [lo,hi): if a[j]<pivot swap(i++,j); swap(i,hi); return i.
 */

import java.util.Arrays;

public class Task01 {

    static void quicksort(int[] a, int lo, int hi) {
        // TODO: базовый случай; p = partition; рекурсия по двум частям
    }

    static int partition(int[] a, int lo, int hi) {
        // TODO: разбиение Ломуто, вернуть индекс pivot
        return lo;
    }

    public static void main(String[] args) {
        int[] a = {3, 7, 2, 8, 5, 1, 6, 4};
        quicksort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
