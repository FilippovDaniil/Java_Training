/**
 * Задача 04 — Тема 18: Все компоненты связности
 *
 * ЗАДАНИЕ:
 *   В НЕориентированном графе найдите все компоненты связности: запускайте
 *   BFS/DFS от каждой ещё не посещённой вершины, каждая такая «волна» — одна
 *   компонента. Верните список компонент (каждая — список вершин).
 *
 * ПРИМЕР / ВЫВОД:
 *   n=6, рёбра (0-1),(1-2),(3-4)
 *   компоненты: [[0,1,2], [3,4], [5]]   (3 штуки)
 *
 * ТРЕБОВАНИЯ:
 *   - граф может быть несвязным; изолированные вершины — отдельные компоненты;
 *   - обход от каждой непосещённой вершины;
 *   - вернуть List<List<Integer>> компонент.
 *
 * ПОДСКАЗКА:
 *   for v in 0..n-1: if !visited[v] -> новый обход, собрать все достижимые в одну компоненту.
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static List<List<Integer>> components(int n, List<List<Integer>> adj) {
        List<List<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[n];
        // TODO: от каждой непосещённой вершины — обход, собрать компоненту
        return comps;
    }

    static List<List<Integer>> build(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        return adj;
    }

    public static void main(String[] args) {
        var adj = build(6, new int[][]{{0,1},{1,2},{3,4}});
        System.out.println("компоненты: " + components(6, adj));
    }
}
