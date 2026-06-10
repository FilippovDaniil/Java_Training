/**
 * Задача 05 — Тема 05: Фибоначчи с мемоизацией
 *
 * ЗАДАНИЕ:
 *   1) fibNaive(n) — наивная рекурсия O(2^n) (дана как эталон).
 *   2) fibMemo(n) — рекурсия с мемоизацией через HashMap -> O(n).
 *   Сравните: fibNaive(40) считается заметно, fibMemo(40) — мгновенно.
 *
 * ПРИМЕР / ВЫВОД:
 *   fibMemo(10) = 55
 *   fibMemo(40) = 102334155
 *   fibNaive(40) = 102334155 (но в разы дольше — почему?)
 *
 * ТРЕБОВАНИЯ:
 *   - fibMemo хранит уже вычисленные значения в Map<Integer, Long>;
 *   - перед вычислением проверяйте, нет ли значения в кэше;
 *   - используйте long (числа быстро растут).
 *
 * ПОДСКАЗКА:
 *   if (memo.containsKey(n)) return memo.get(n);
 *   long v = fibMemo(n-1) + fibMemo(n-2); memo.put(n, v); return v;
 */

import java.util.HashMap;
import java.util.Map;

public class Task05 {

    static long fibNaive(int n) {
        if (n < 2) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    static final Map<Integer, Long> memo = new HashMap<>();

    static long fibMemo(int n) {
        // TODO: базовый случай n<2 -> n; проверить memo; иначе посчитать, сохранить, вернуть
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("fibMemo(10) = " + fibMemo(10));
        System.out.println("fibMemo(40) = " + fibMemo(40));
        // TODO: при желании замерьте время fibNaive(40) против fibMemo(40)
    }
}
