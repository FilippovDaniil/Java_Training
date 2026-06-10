/**
 * Задача 01 — Тема 22: Фибоначчи мемоизацией и табуляцией
 *
 * ЗАДАНИЕ:
 *   Реализуйте число Фибоначчи ДВУМЯ способами DP:
 *     - fibMemo (top-down: рекурсия + кэш);
 *     - fibTab (bottom-up: заполнение массива в цикле).
 *   Оба за O(n). Сравните с наивной рекурсией O(2^n) (дана как эталон).
 *
 * ПРИМЕР / ВЫВОД:
 *   fibMemo(10) = 55, fibTab(10) = 55
 *   fibTab(50) = 12586269025
 *
 * ТРЕБОВАНИЯ:
 *   - использовать long (значения быстро растут);
 *   - memo — массив/Map; tab — массив dp[0..n];
 *   - база: f(0)=0, f(1)=1.
 *
 * ПОДСКАЗКА:
 *   Табуляция: dp[i] = dp[i-1] + dp[i-2]; можно ужать память до двух переменных.
 */

public class Task01 {

    static long[] memo;

    static long fibMemo(int n) {
        // TODO: top-down с кэшем memo
        return 0;
    }

    static long fibTab(int n) {
        // TODO: bottom-up табуляция
        return 0;
    }

    public static void main(String[] args) {
        memo = new long[51];
        java.util.Arrays.fill(memo, -1);
        System.out.println("fibMemo(10) = " + fibMemo(10));
        System.out.println("fibTab(10)  = " + fibTab(10));
        System.out.println("fibTab(50)  = " + fibTab(50));
    }
}
