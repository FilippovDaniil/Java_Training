package m06_arrays.practice;

/**
 * Задача 01 — Модуль 06: Создание и вывод массива
 *
 * ЗАДАНИЕ:
 *   Создайте массив из 5 целых чисел {10, 20, 30, 40, 50}.
 *   Выведите все элементы через пробел, используя цикл.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   10 20 30 40 50
 *
 * ПОДСКАЗКА:
 *   for (int i = 0; i < a.length; i++) { ... a[i] ... }
 */
public class Task01 {
    public static void main(String[] args) {
        int[] a = {10, 20, 30, 40, 50};
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
