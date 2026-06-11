package m14_collections_set_iterator.practice;

/**
 * Задача 04 — Модуль 14: Итератор и безопасное удаление
 *
 * ЗАДАНИЕ:
 *   Дан список чисел. Используя ИТЕРАТОР (не for-each), удалите из
 *   списка все отрицательные числа. Выведите список до и после.
 *
 * ПРИМЕР:
 *   До:    [3, -1, 4, -5, 9, -2, 6]
 *   После: [3, 4, 9, 6]
 *
 * ВНИМАНИЕ:
 *   Удалять элементы в обычном for-each нельзя — будет
 *   ConcurrentModificationException. Используйте it.remove().
 *
 * ПОДСКАЗКА:
 *   Iterator<Integer> it = list.iterator();
 *   while (it.hasNext()) { int x = it.next(); if (x < 0) it.remove(); }
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Task04 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(3, -1, 4, -5, 9, -2, 6));
        // Ваш код здесь
    }
}
