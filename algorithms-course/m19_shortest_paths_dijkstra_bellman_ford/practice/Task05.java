package m19_shortest_paths_dijkstra_bellman_ford.practice;

/**
 * Задача 05 — Тема 19: Дейкстра за O(V²) для плотных графов
 *
 * ЗАДАНИЕ:
 *   Реализуйте Дейкстру БЕЗ приоритетной очереди: на каждом шаге выбирайте
 *   ближайшую нефинализированную вершину линейным поиском по dist (O(V)),
 *   итого O(V²). Для ПЛОТНЫХ графов (E ≈ V²) это не хуже версии с кучей,
 *   а порой и быстрее (меньше накладных расходов).
 *
 * ПРИМЕР / ВЫВОД:
 *   матрица смежности 4x4 (0 = нет ребра), веса неотрицательные
 *   dist от 0 совпадает с результатом Дейкстры из задачи 01
 *
 * ТРЕБОВАНИЯ:
 *   - граф задан матрицей смежности (вес или 0/INF при отсутствии ребра);
 *   - на каждой из V итераций — линейный выбор минимума + релаксация соседей;
 *   - массив visited для финализированных вершин.
 *
 * ПОДСКАЗКА:
 *   for i in 0..V-1: u = argmin dist среди !visited; visited[u]=true; релаксировать всех соседей.
 */

public class Task05 {

    static final int INF = Integer.MAX_VALUE;

    static int[] dijkstraDense(int[][] w, int start) {
        int n = w.length;
        int[] dist = new int[n];
        java.util.Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean[] visited = new boolean[n];
        // TODO: V раз — выбрать ближайшую нефинализированную вершину, релаксировать соседей
        return dist;
    }

    public static void main(String[] args) {
        // 0 = нет ребра; иначе вес
        int[][] w = {
            {0, 4, 1, 0},
            {4, 0, 2, 1},
            {1, 2, 0, 5},
            {0, 1, 5, 0}
        };
        System.out.println(java.util.Arrays.toString(dijkstraDense(w, 0)));   // [0, 3, 1, 4]
    }
}
