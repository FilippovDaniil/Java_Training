/**
 * Задача 01 — Тема 06: Линейный поиск — первое и последнее вхождение
 *
 * ЗАДАНИЕ:
 *   Реализуйте firstIndexOf(a, target) и lastIndexOf(a, target) линейным
 *   поиском. Если элемента нет — вернуть -1.
 *
 * ПРИМЕР / ВЫВОД:
 *   a = [2, 5, 5, 5, 8], target = 5
 *   first = 1, last = 3
 *   target = 9 -> first = -1, last = -1
 *
 * ТРЕБОВАНИЯ:
 *   - оба метода — O(n);
 *   - массив НЕ обязан быть отсортирован;
 *   - first возвращает наименьший индекс, last — наибольший.
 *
 * ПОДСКАЗКА:
 *   first: верните сразу при первом совпадении.
 *   last: идите с конца ИЛИ запоминайте последний найденный индекс.
 */

public class Task01 {

    static int firstIndexOf(int[] a, int target) {
        // TODO
        return -1;
    }

    static int lastIndexOf(int[] a, int target) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 5, 5, 5, 8};
        System.out.println("first(5) = " + firstIndexOf(a, 5));
        System.out.println("last(5) = " + lastIndexOf(a, 5));
        System.out.println("first(9) = " + firstIndexOf(a, 9));
    }
}
