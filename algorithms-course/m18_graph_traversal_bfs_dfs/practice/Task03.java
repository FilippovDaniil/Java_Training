package m18_graph_traversal_bfs_dfs.practice;

/**
 * Задача 03 — Тема 18: BFS для кратчайшего пути (восстановить путь s->t)
 *
 * ЗАДАНИЕ:
 *   В НЕвзвешенном графе найдите кратчайший путь (по числу рёбер) от s до t
 *   с помощью BFS и ВОССТАНОВИТЕ сам путь, запоминая родителей.
 *
 * ПРИМЕР / ВЫВОД:
 *   граф (неориентир.): 0-1, 1-2, 2-3, 0-3
 *   shortestPath(0, 2) -> [0, 1, 2] (или [0, 3, 2] — оба длины 2)
 *   shortestPath(0, 0) -> [0]
 *   нет пути -> пустой список
 *
 * ТРЕБОВАНИЯ:
 *   - BFS от s; массив parent для восстановления;
 *   - путь строится с конца (t -> parent -> ... -> s), затем разворачивается;
 *   - если t недостижима — вернуть пустой список.
 *
 * ПОДСКАЗКА:
 *   parent[start] = -1 (нет родителя). Восстановление: идём от t по parent до -1, reverse.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Task03 {

    static List<Integer> shortestPath(int s, int t, List<List<Integer>> adj) {
        int n = adj.size();
        int[] parent = new int[n];
        java.util.Arrays.fill(parent, -2);   // -2 = не посещена
        // TODO: BFS от s, заполняя parent; восстановить путь s->t
        return new ArrayList<>();
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(4, new int[][]{{0,1},{1,2},{2,3},{0,3}});
        System.out.println("путь 0->2: " + shortestPath(0, 2, adj));
        System.out.println("путь 0->0: " + shortestPath(0, 0, adj));
    }
}
