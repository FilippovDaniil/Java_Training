package m05_recursion_iteration.practice;

/**
 * Задача 06 — Тема 05: Сравнить рекурсию и итерацию для больших n
 *
 * ЗАДАНИЕ:
 *   Возьмите задачу суммирования 1..n. Реализуйте sumRec(n) (рекурсивно) и
 *   sumIter(n) (циклом). Сравните:
 *     - время работы для среднего n (замер через System.nanoTime);
 *     - поведение для ОЧЕНЬ большого n: sumRec упадёт с StackOverflowError
 *       (глубина рекурсии = n), а sumIter — нет.
 *
 * ПРИМЕР / ВЫВОД:
 *   sumIter(1000000) = 500000500000
 *   sumRec(1000000): StackOverflowError (глубина рекурсии ~ n)
 *   Вывод: итерация — O(1) память, рекурсия — O(n) память на стек.
 *
 * ТРЕБОВАНИЯ:
 *   - sumRec: базовый случай n==0 -> 0; иначе n + sumRec(n-1);
 *   - продемонстрируйте StackOverflowError в try/catch для большого n;
 *   - используйте long для суммы.
 *
 * ПОДСКАЗКА:
 *   StackOverflowError ловится через catch (StackOverflowError e).
 */

public class Task06 {

    static long sumRec(int n) {
        // TODO: рекурсивно
        return 0;
    }

    static long sumIter(int n) {
        // TODO: цикл
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("sumIter(1000000) = " + sumIter(1_000_000));
        // TODO: в try/catch вызовите sumRec(1_000_000) и поймайте StackOverflowError
    }
}
