/**
 * Задача 02 — Тема 01: findDuplicate — оценить и улучшить
 *
 * ЗАДАНИЕ:
 *   1) Реализуйте findDuplicateSlow: найти ЛЮБОЙ повторяющийся элемент
 *      массива, перебирая все пары (вложенные циклы). Оцените сложность.
 *   2) Реализуйте findDuplicateFast: та же задача, но за O(n) по времени
 *      с использованием HashSet (память O(n)).
 *   В TODO-комментариях укажите сложность обоих методов.
 *
 * ПРИМЕР / ВЫВОД:
 *   Вход: [3, 1, 4, 1, 5]
 *   Slow: найден дубликат 1   (сложность O(n^2))
 *   Fast: найден дубликат 1   (сложность O(n), память O(n))
 *
 * ТРЕБОВАНИЯ:
 *   - если дубликатов нет — верните -1 (или Integer.MIN_VALUE);
 *   - Slow: только массив, без доп. коллекций;
 *   - Fast: один проход, HashSet для уже встреченных.
 *
 * ПОДСКАЗКА:
 *   Fast: идём по массиву, для каждого элемента проверяем set.contains(x);
 *   если да — это дубликат; иначе set.add(x).
 */

import java.util.HashSet;
import java.util.Set;

public class Task02 {

    // TODO: сложность? ___
    static int findDuplicateSlow(int[] a) {
        // TODO: два вложенных цикла, сравнить a[i] и a[j] при j>i
        return -1;
    }

    // TODO: сложность? ___ , память? ___
    static int findDuplicateFast(int[] a) {
        Set<Integer> seen = new HashSet<>();
        // TODO: один проход; вернуть первый элемент, который уже в seen
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {3, 1, 4, 1, 5};
        System.out.println("Slow: " + findDuplicateSlow(data));
        System.out.println("Fast: " + findDuplicateFast(data));
    }
}
