package m10_counting_radix_sort.practice;

/**
 * Задача 04 — Тема 10: Radix sort для строк одинаковой длины vs Arrays.sort
 *
 * ЗАДАНИЕ:
 *   Отсортируйте массив строк ОДИНАКОВОЙ длины LSD radix sort'ом (по символам
 *   справа налево, стабильный counting по коду символа). Сравните результат и
 *   время с java.util.Arrays.sort (которая использует сравнение).
 *
 * ПРИМЕР / ВЫВОД:
 *   До:    [dca, abc, bbb, aaa, cba]
 *   radix: [aaa, abc, bbb, cba, dca]
 *   Arrays.sort даёт тот же порядок (проверка корректности).
 *
 * ТРЕБОВАНИЯ:
 *   - все строки одной длины L;
 *   - проходы по позициям символов от L-1 до 0, каждый — стабильный counting;
 *   - алфавит можно ограничить ASCII (256 корзин);
 *   - сверьте результат с Arrays.sort(копия).
 *
 * ПОДСКАЗКА:
 *   countingSortByChar(arr, pos): корзины по (int) s.charAt(pos), стабильно.
 */

import java.util.Arrays;

public class Task04 {

    static void radixSort(String[] a) {
        // TODO: для pos от L-1 до 0 — стабильный counting по символу
    }

    public static void main(String[] args) {
        String[] a = {"dca", "abc", "bbb", "aaa", "cba"};
        String[] expected = a.clone();
        Arrays.sort(expected);
        radixSort(a);
        System.out.println("radix:    " + Arrays.toString(a));
        System.out.println("expected: " + Arrays.toString(expected));
        System.out.println("совпало: " + Arrays.equals(a, expected));
    }
}
