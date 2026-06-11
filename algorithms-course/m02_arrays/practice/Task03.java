package m02_arrays.practice;

/**
 * Задача 03 — Тема 02: reverse(int[]) in-place
 *
 * ЗАДАНИЕ:
 *   Разверните массив НА МЕСТЕ (без создания нового массива), память O(1).
 *   Используйте два указателя, идущих с краёв навстречу.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [1, 2, 3, 4, 5]
 *   После: [5, 4, 3, 2, 1]
 *
 * ТРЕБОВАНИЯ:
 *   - метод reverse(int[] a) меняет переданный массив, ничего не возвращает;
 *   - никакого нового массива и коллекций — только обмен элементов;
 *   - корректно работает для пустого массива и массива из 1 элемента.
 *
 * ПОДСКАЗКА:
 *   l=0, r=a.length-1; пока l<r — swap(a[l],a[r]), l++, r--.
 */

import java.util.Arrays;

public class Task03 {

    static void reverse(int[] a) {
        // TODO: два указателя навстречу, обмен элементов
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        System.out.println("До:    " + Arrays.toString(a));
        reverse(a);
        System.out.println("После: " + Arrays.toString(a));
    }
}
