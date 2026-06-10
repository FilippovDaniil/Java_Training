/**
 * Задача 04 — Тема 02: Циклический сдвиг влево на k (три способа)
 *
 * ЗАДАНИЕ:
 *   Реализуйте циклический сдвиг массива влево на k позиций ТРЕМЯ способами:
 *     1) rotateExtra  — через дополнительный массив (O(n) время, O(n) память);
 *     2) rotateOneByOne — k раз сдвигать на 1 (O(n*k) время, O(1) память);
 *     3) rotateReverse — через тройной reverse (O(n) время, O(1) память).
 *   Все три должны давать одинаковый результат.
 *
 * ПРИМЕР / ВЫВОД:
 *   Вход: [1,2,3,4,5], k=2
 *   Результат всех трёх: [3,4,5,1,2]
 *
 * ТРЕБОВАНИЯ:
 *   - учтите k > n: используйте k % n;
 *   - тройной reverse: reverse(0,k-1), reverse(k,n-1), reverse(0,n-1);
 *   - методы не должны портить вход для следующего способа (работайте с копией).
 *
 * ПОДСКАЗКА:
 *   Сдвиг ВЛЕВО на k: элемент с индекса i уходит на (i-k+n)%n.
 *   Тройной reverse именно в таком порядке даёт сдвиг влево.
 */

import java.util.Arrays;

public class Task04 {

    static int[] rotateExtra(int[] a, int k) {
        // TODO: новый массив, b[(i - k + n) % n] = a[i] (или эквивалентно)
        return a.clone();
    }

    static int[] rotateOneByOne(int[] a, int k) {
        // TODO: k раз сдвинуть массив на 1 влево (с переносом первого в конец)
        return a.clone();
    }

    static int[] rotateReverse(int[] a, int k) {
        // TODO: тройной reverse на копии массива
        return a.clone();
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int k = 2;
        System.out.println("extra:    " + Arrays.toString(rotateExtra(a, k)));
        System.out.println("oneByOne: " + Arrays.toString(rotateOneByOne(a, k)));
        System.out.println("reverse:  " + Arrays.toString(rotateReverse(a, k)));
    }
}
