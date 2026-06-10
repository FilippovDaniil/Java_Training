/**
 * Задача 01 — Тема 25: Подсчёт частот символов
 *
 * ЗАДАНИЕ:
 *   Посчитайте, сколько раз встречается каждый символ строки. Это первый шаг
 *   алгоритма Хаффмана. Верните отображение символ -> частота.
 *
 * ПРИМЕР / ВЫВОД:
 *   "aaabbc" -> {a=3, b=2, c=1}
 *   "" -> {}
 *
 * ТРЕБОВАНИЯ:
 *   - вернуть Map<Character, Integer> (или int[256] для ASCII);
 *   - один проход O(n);
 *   - вывод частот в любом порядке.
 *
 * ПОДСКАЗКА:
 *   map.merge(ch, 1, Integer::sum) — компактный способ инкремента счётчика.
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class Task01 {

    static Map<Character, Integer> frequencies(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        // TODO: один проход, считать частоты
        return freq;
    }

    public static void main(String[] args) {
        System.out.println(frequencies("aaabbc"));   // {a=3, b=2, c=1}
        System.out.println(frequencies(""));          // {}
    }
}
