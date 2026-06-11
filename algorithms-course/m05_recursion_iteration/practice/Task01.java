package m05_recursion_iteration.practice;

/**
 * Задача 01 — Тема 05: Рекурсивная сумма цифр числа
 *
 * ЗАДАНИЕ:
 *   Напишите РЕКУРСИВНЫЙ метод digitSum(n), возвращающий сумму цифр
 *   неотрицательного целого n. Затем напишите итеративную версию digitSumIter
 *   для сравнения.
 *
 * ПРИМЕР / ВЫВОД:
 *   digitSum(12345) = 15
 *   digitSum(0) = 0
 *   digitSum(7) = 7
 *
 * ТРЕБОВАНИЯ:
 *   - рекурсия: базовый случай n < 10 -> n; шаг: n%10 + digitSum(n/10);
 *   - обе версии дают одинаковый ответ;
 *   - n >= 0 (можно не поддерживать отрицательные).
 *
 * ПОДСКАЗКА:
 *   Последняя цифра — n % 10; «отрезать» её — n / 10.
 */

public class Task01 {

    static int digitSum(int n) {
        // TODO: рекурсивно
        return 0;
    }

    static int digitSumIter(int n) {
        // TODO: цикл while (n > 0)
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("digitSum(12345) = " + digitSum(12345));
        System.out.println("digitSumIter(12345) = " + digitSumIter(12345));
    }
}
