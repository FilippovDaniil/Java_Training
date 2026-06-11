package m10_counting_radix_sort.practice;

/**
 * Задача 05 — Тема 10: Radix sort для int[] по байтам (4 прохода)
 *
 * ЗАДАНИЕ:
 *   Отсортируйте массив неотрицательных int, обрабатывая число не по
 *   десятичным цифрам, а по группам БИТ: 4 прохода по 8 бит (k = 256 корзин).
 *   Это типичная высокопроизводительная реализация radix sort для int.
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [256, 1, 65536, 2, 16777216, 0]
 *   После: [0, 1, 2, 256, 65536, 16777216]
 *
 * ТРЕБОВАНИЯ:
 *   - 4 прохода: байты 0..3, маска 0xFF, сдвиг shift = 8*проход;
 *   - каждый проход — стабильный counting по байту (a[i] >>> shift) & 0xFF;
 *   - для простоты рассматриваем только неотрицательные числа.
 *
 * ПОДСКАЗКА:
 *   for (shift=0; shift<32; shift+=8) countingByByte(a, shift);
 *   цифра = (a[i] >>> shift) & 0xFF.
 */

import java.util.Arrays;

public class Task05 {

    static void radixSort(int[] a) {
        // TODO: 4 прохода по байтам, каждый — стабильный counting (256 корзин)
    }

    public static void main(String[] args) {
        int[] a = {256, 1, 65536, 2, 16777216, 0};
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }
}
