package m08_merge_sort.practice;

/**
 * Задача 02 — Тема 08: Итеративная (восходящая) сортировка слиянием
 *
 * ЗАДАНИЕ:
 *   Реализуйте merge sort БЕЗ рекурсии. Сливайте подмассивы возрастающей
 *   ширины: сначала width=1 (пары одиночных элементов), затем 2, 4, 8...
 *   пока width < n.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2, 7, 4, 6]
 *   После: [1, 2, 3, 4, 5, 6, 7, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - без рекурсии: внешний цикл по width (1,2,4,...), внутренний — по началам блоков;
 *   - корректно обработайте «хвост», когда блок выходит за границу массива
 *     (mid и hi ограничивайте через Math.min);
 *   - используйте ту же функцию merge, что и в задаче 01.
 *
 * ПОДСКАЗКА:
 *   for (width=1; width<n; width*=2)
 *     for (lo=0; lo<n; lo+=2*width) { mid=min(lo+width-1, n-1); hi=min(lo+2*width-1, n-1); merge(...); }
 */

import java.util.Arrays;

public class Task02 {

    static void mergeSortIterative(int[] a) {
        // TODO: восходящее слияние блоками возрастающей ширины
    }

    static void merge(int[] a, int lo, int mid, int hi) {
        // TODO: как в задаче 01
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2, 7, 4, 6};
        mergeSortIterative(a);
        System.out.println(Arrays.toString(a));
    }
}
