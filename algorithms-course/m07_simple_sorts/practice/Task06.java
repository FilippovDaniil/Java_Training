package m07_simple_sorts.practice;

/**
 * Задача 06 — Тема 07: Подсчёт сравнений и обменов
 *
 * ЗАДАНИЕ:
 *   Доработайте три сортировки (пузырёк, выбором, вставками) так, чтобы они
 *   считали число СРАВНЕНИЙ и число ОБМЕНОВ (для вставок — сдвигов). Прогоните
 *   все три на одном и том же массиве и выведите таблицу.
 *
 * ПРИМЕР / ВЫВОД (для [5,3,8,1,2]):
 *   bubble:    сравнений=10, обменов=6
 *   selection: сравнений=10, обменов=4
 *   insertion: сравнений=..., сдвигов=...
 *   (числа — для примера; важно, что выбором делает меньше обменов)
 *
 * ТРЕБОВАНИЯ:
 *   - каждая сортировка возвращает пару (сравнения, обмены) — например long[2];
 *   - сортируйте КОПИЮ массива (чтобы сравнить на одинаковом входе);
 *   - сделайте вывод: у какой сортировки меньше обменов, у какой — сравнений.
 *
 * ПОДСКАЗКА:
 *   Заведите счётчики и увеличивайте их в точках сравнения (if a[..]>a[..]) и обмена.
 */

import java.util.Arrays;

public class Task06 {

    // каждый метод возвращает {сравнения, обмены/сдвиги}
    static long[] bubble(int[] a) {
        // TODO
        return new long[2];
    }
    static long[] selection(int[] a) {
        // TODO
        return new long[2];
    }
    static long[] insertion(int[] a) {
        // TODO
        return new long[2];
    }

    public static void main(String[] args) {
        int[] base = {5, 3, 8, 1, 2};
        System.out.println("bubble:    " + Arrays.toString(bubble(base.clone())));
        System.out.println("selection: " + Arrays.toString(selection(base.clone())));
        System.out.println("insertion: " + Arrays.toString(insertion(base.clone())));
    }
}
