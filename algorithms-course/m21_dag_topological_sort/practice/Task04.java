package m21_dag_topological_sort.practice;

/**
 * Задача 04 — Тема 21: Проверка ацикличности через топологическую сортировку
 *
 * ЗАДАНИЕ:
 *   Определите, является ли ОРиентированный граф ациклическим (DAG), используя
 *   алгоритм Кана: если топологическая сортировка обработала ВСЕ вершины —
 *   граф ацикличен; если осталась хотя бы одна (с ненулевым in-degree) — есть цикл.
 *
 * ПРИМЕР / ВЫВОД:
 *   0->1, 1->2, 2->0  -> isDag: false (цикл)
 *   0->1, 1->2, 0->2  -> isDag: true  (DAG)
 *
 * ТРЕБОВАНИЯ:
 *   - использовать Кан (in-degree, очередь истоков);
 *   - считать число обработанных вершин; isDag = (обработано == n);
 *   - корректно для несвязного графа.
 *
 * ПОДСКАЗКА:
 *   Цикл «запирает» свои вершины: их in-degree никогда не дойдёт до 0.
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static boolean isDag(int n, List<List<Integer>> adj) {
        // TODO: Кан; вернуть (число обработанных вершин == n)
        return false;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        System.out.println("цикл: " + isDag(3, build(3, new int[][]{{0,1},{1,2},{2,0}})));
        System.out.println("DAG:  " + isDag(3, build(3, new int[][]{{0,1},{1,2},{0,2}})));
    }
}
