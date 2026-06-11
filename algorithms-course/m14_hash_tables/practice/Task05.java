package m14_hash_tables.practice;

/**
 * Задача 05 — Тема 14: Сравнить свою хеш-таблицу с java.util.HashMap
 *
 * ЗАДАНИЕ:
 *   Сравните производительность вашей хеш-таблицы (цепочки) и стандартной
 *   java.util.HashMap на большом числе операций put/get. Замерьте время
 *   вставки N элементов и N выборок. Сделайте вывод (стандартная обычно
 *   быстрее за счёт оптимизаций — это нормально; важно, что ваша даёт O(1) в среднем).
 *
 * ПРИМЕР / ВЫВОД (числа зависят от железа):
 *   N=1_000_000
 *   MyHashMap:        put=..ms get=..ms
 *   java.util.HashMap put=..ms get=..ms
 *
 * ТРЕБОВАНИЯ:
 *   - используйте свою реализацию с рехешированием (иначе цепочки выродятся в O(n));
 *   - одинаковые данные для обеих;
 *   - замеряйте put и get отдельно; не забудьте прогрев.
 *
 * ПОДСКАЗКА:
 *   Для честности дайте своей таблице расти (resize), иначе сравнение нечестное.
 */

import java.util.HashMap;
import java.util.Map;

public class Task05 {

    // упрощённая своя таблица с рехешированием (реализуйте методы)
    static class SimpleHashMap {
        static class E { int k, v; E next; E(int k,int v){this.k=k;this.v=v;} }
        private E[] b = new E[16];
        private int size;
        private int idx(int k, int cap){ return (Integer.hashCode(k)&0x7fffffff)%cap; }
        void put(int k, int v) {
            // TODO: вставка + resize при load factor > 0.75
        }
        Integer get(int k) {
            // TODO: поиск в цепочке
            return null;
        }
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        SimpleHashMap mine = new SimpleHashMap();
        Map<Integer,Integer> std = new HashMap<>();
        // TODO: замерить put n элементов и get n элементов для обеих, вывести времена
    }
}
