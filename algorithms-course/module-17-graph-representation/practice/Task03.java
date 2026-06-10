/**
 * Задача 03 — Тема 17: Загрузка графа из списка рёбер
 *
 * ЗАДАНИЕ:
 *   Постройте граф (список смежности) из текстового описания: первая строка —
 *   "n m" (число вершин и рёбер), далее m строк "u v" — рёбра. Для изоляции
 *   читаем из строки, а не из файла. Затем выведите списки смежности.
 *
 * ПРИМЕР / ВЫВОД:
 *   вход:
 *     4 3
 *     0 1
 *     0 2
 *     2 3
 *   соседи: 0->[1,2], 1->[0], 2->[0,3], 3->[2]
 *
 * ТРЕБОВАНИЯ:
 *   - распарсить n, m и m рёбер; граф неориентированный;
 *   - построить List<List<Integer>>;
 *   - вывести соседей каждой вершины.
 *
 * ПОДСКАЗКА:
 *   text.split("\\R") -> строки; первая строка -> n,m; остальные -> рёбра.
 *   Integer.parseInt по токенам строки (split на пробелы).
 */

import java.util.ArrayList;
import java.util.List;

public class Task03 {

    static List<List<Integer>> parseGraph(String text) {
        // TODO: разобрать "n m" + m рёбер; построить список смежности (неориентир.)
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String input = "4 3\n0 1\n0 2\n2 3";
        List<List<Integer>> adj = parseGraph(input);
        for (int u = 0; u < adj.size(); u++)
            System.out.println(u + " -> " + adj.get(u));
    }
}
