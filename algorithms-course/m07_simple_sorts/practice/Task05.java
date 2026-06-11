package m07_simple_sorts.practice;

/**
 * Задача 05 — Тема 07: Вставки со сдвигом через System.arraycopy
 *
 * ЗАДАНИЕ:
 *   Реализуйте сортировку вставками, где сдвиг блока элементов вправо делается
 *   не поэлементным циклом, а одним вызовом System.arraycopy. Сначала найдите
 *   позицию вставки (можно бинарным поиском в отсортированной части),
 *   затем сдвиньте хвост и вставьте элемент.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [5, 3, 8, 1, 2]
 *   После: [1, 2, 3, 5, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - сдвиг элементов делать через System.arraycopy(a, pos, a, pos+1, count);
 *   - позицию вставки можно искать линейно или бинарным поиском (binary insertion sort);
 *   - результат идентичен обычной insertion sort.
 *
 * ПОДСКАЗКА:
 *   key=a[i]; pos = место в [0..i), куда вставить key;
 *   System.arraycopy(a, pos, a, pos+1, i-pos); a[pos]=key.
 */

import java.util.Arrays;

public class Task05 {

    static void insertionSortArraycopy(int[] a) {
        // TODO: для каждого i найти позицию вставки, сдвинуть хвост arraycopy, вставить
    }

    public static void main(String[] args) {
        int[] a = {5, 3, 8, 1, 2};
        insertionSortArraycopy(a);
        System.out.println(Arrays.toString(a));
    }
}
