package m11_substring_search.practice;

/**
 * Задача 01 — Тема 11: Наивный поиск подстроки (все вхождения)
 *
 * ЗАДАНИЕ:
 *   Реализуйте наивный поиск: верните список индексов ВСЕХ вхождений паттерна
 *   P в текст T (вхождения могут перекрываться).
 *
 * ПРИМЕР / ВЫВОД:
 *   T = "ABABABC", P = "ABA"
 *   вхождения: [0, 2]   (ABA на позициях 0 и 2 — перекрываются)
 *   P = "XY" -> []
 *
 * ТРЕБОВАНИЯ:
 *   - O(n·m); сравнивать паттерн посимвольно для каждой стартовой позиции;
 *   - возвращать все стартовые индексы (List<Integer>);
 *   - учесть перекрытия (после совпадения сдвиг всё равно на 1).
 *
 * ПОДСКАЗКА:
 *   for i in [0, n-m]: j=0; while j<m && T[i+j]==P[j] j++; if j==m add(i).
 */

import java.util.ArrayList;
import java.util.List;

public class Task01 {

    static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        // TODO: наивный перебор стартовых позиций
        return result;
    }

    public static void main(String[] args) {
        System.out.println(search("ABABABC", "ABA"));   // [0, 2]
        System.out.println(search("ABABABC", "XY"));     // []
    }
}
