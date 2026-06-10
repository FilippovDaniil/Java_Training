/**
 * Задача 03 — Тема 06: Бинарный поиск левой границы (первое появление)
 *
 * ЗАДАНИЕ:
 *   В отсортированном массиве с ДУБЛИКАТАМИ найдите индекс ПЕРВОГО вхождения
 *   target за O(log n) (а не O(n) линейным проходом). Если нет — верните -1.
 *
 * ПРИМЕР / ВЫВОД:
 *   a = [1, 2, 2, 2, 3, 4], target = 2
 *   firstOccurrence -> 1   (первая из трёх двоек)
 *   target = 5 -> -1
 *
 * ТРЕБОВАНИЯ:
 *   - O(log n); не сканировать соседей линейно после нахождения;
 *   - при совпадении НЕ останавливаться — продолжать сужать левую границу;
 *   - в конце проверить, что найденный индекс действительно содержит target.
 *
 * ПОДСКАЗКА:
 *   while (lo < hi) { mid; if (a[mid] < target) lo=mid+1; else hi=mid; }
 *   После цикла lo — кандидат; проверьте a[lo]==target.
 */

public class Task03 {

    static int firstOccurrence(int[] a, int target) {
        // TODO: бинпоиск левой границы; вернуть -1, если target отсутствует
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 2, 3, 4};
        System.out.println("firstOccurrence(2) = " + firstOccurrence(a, 2));
        System.out.println("firstOccurrence(5) = " + firstOccurrence(a, 5));
    }
}
