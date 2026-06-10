/**
 * Задача 02 — Тема 10: Counting sort с поддержкой отрицательных чисел
 *
 * ЗАДАНИЕ:
 *   Расширьте counting sort на отрицательные значения через СДВИГ: найдите
 *   min и max, заведите count размера (max - min + 1), индексируйте (x - min).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [-3, 1, -1, 0, 2, -3]
 *   После: [-3, -3, -1, 0, 1, 2]
 *
 * ТРЕБОВАНИЯ:
 *   - корректно при любых int (в разумном диапазоне max-min);
 *   - стабильность сохраняется;
 *   - при восстановлении значения не забудьте прибавить min обратно.
 *
 * ПОДСКАЗКА:
 *   k = max - min + 1; count[x - min]++; при раскладке значение = индекс + min.
 */

import java.util.Arrays;

public class Task02 {

    static int[] countingSort(int[] a) {
        // TODO: найти min/max; count размера max-min+1; сортировка со сдвигом
        return a.clone();
    }

    public static void main(String[] args) {
        int[] a = {-3, 1, -1, 0, 2, -3};
        System.out.println(Arrays.toString(countingSort(a)));
    }
}
