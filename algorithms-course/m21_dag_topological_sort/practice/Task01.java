package m21_dag_topological_sort.practice;

/**
 * Задача 01 — Тема 21: Топологическая сортировка (алгоритм Кана)
 *
 * ЗАДАНИЕ:
 *   Постройте топологический порядок DAG алгоритмом Кана: считайте in-degree,
 *   начинайте с вершин с in-degree 0, удаляйте их, уменьшая степени соседей.
 *
 * ПРИМЕР / ВЫВОД:
 *   рёбра: 5->2, 5->0, 4->0, 4->1, 2->3, 3->1
 *   топопорядок (один из возможных): [4, 5, 2, 3, 1, 0]
 *   (каждая вершина раньше всех, на кого указывает)
 *
 * ТРЕБОВАНИЯ:
 *   - in-degree всех вершин; очередь вершин с indeg 0;
 *   - извлекая u, добавлять в порядок и уменьшать indeg соседей;
 *   - если в итоге обработаны не все вершины — в графе цикл (вернуть пустой/пометку).
 *
 * ПОДСКАЗКА:
 *   В очередь сразу кладите ВСЕ вершины с indeg 0 (истоков может быть несколько).
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task01 {

    static List<Integer> topoSort(int n, List<List<Integer>> adj) {
        // TODO: посчитать indeg; очередь истоков; Кан
        return new ArrayList<>();
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(6, new int[][]{{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}});
        System.out.println("топопорядок: " + topoSort(6, adj));
    }
}
