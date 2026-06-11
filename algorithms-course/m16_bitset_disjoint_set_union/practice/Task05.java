package m16_bitset_disjoint_set_union.practice;

/**
 * Задача 05 — Тема 16: Решето Эратосфена на битовом массиве
 *
 * ЗАДАНИЕ:
 *   Найдите все простые числа до N, используя решето Эратосфена, где признак
 *   «составное» хранится в БИТОВОМ массиве (long[]), а не в boolean[] —
 *   экономия памяти в ~8 раз.
 *
 * ПРИМЕР / ВЫВОД:
 *   N=30
 *   простые: 2 3 5 7 11 13 17 19 23 29
 *   количество простых до 30: 10
 *
 * ТРЕБОВАНИЯ:
 *   - бит i == 1 означает «i составное»; 0 и 1 — не простые;
 *   - для каждого простого p (начиная с 2) помечать p*p, p*p+p, ... как составные;
 *   - перебор p можно до sqrt(N);
 *   - вернуть список простых (или их количество).
 *
 * ПОДСКАЗКА:
 *   Можно использовать java.util.BitSet или свой long[]. Помечать с p*p
 *   (меньшие кратные уже помечены меньшими простыми).
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {

    static List<Integer> sieve(int n) {
        List<Integer> primes = new ArrayList<>();
        // TODO: битовый массив composite размера n+1; решето; собрать простые
        return primes;
    }

    public static void main(String[] args) {
        List<Integer> primes = sieve(30);
        System.out.println("простые: " + primes);
        System.out.println("количество: " + primes.size());
    }
}
