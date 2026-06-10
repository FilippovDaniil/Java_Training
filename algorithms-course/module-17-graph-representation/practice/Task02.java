/**
 * Задача 02 — Тема 17: Граф через матрицу смежности
 *
 * ЗАДАНИЕ:
 *   Реализуйте граф на МАТРИЦЕ смежности (boolean[n][n] или int[n][n]) с
 *   addEdge, hasEdge (за O(1)) и neighbors(u). Сравните с задачей 01:
 *   здесь проверка «есть ли ребро» — O(1), но память — O(V²).
 *
 * ПРИМЕР / ВЫВОД:
 *   n=4, рёбра (0-1),(0-2),(2-3)  (неориентированный)
 *   hasEdge(0,2) -> true
 *   hasEdge(1,3) -> false
 *   соседи 2: [0, 3]
 *
 * ТРЕБОВАНИЯ:
 *   - матрица n×n; неориентированное ребро ставит m[u][v] и m[v][u];
 *   - hasEdge — O(1); neighbors — O(V) (просмотр строки);
 *   - память O(V²).
 *
 * ПОДСКАЗКА:
 *   neighbors(u): пройти строку m[u], собрать индексы v, где m[u][v] установлено.
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    static class MatrixGraph {
        private final boolean[][] m;
        MatrixGraph(int n) { m = new boolean[n][n]; }

        void addEdge(int u, int v) {
            // TODO: m[u][v]=true; m[v][u]=true;
        }
        boolean hasEdge(int u, int v) {
            // TODO
            return false;
        }
        List<Integer> neighbors(int u) {
            List<Integer> res = new ArrayList<>();
            // TODO: собрать v, где m[u][v]
            return res;
        }
    }

    public static void main(String[] args) {
        MatrixGraph g = new MatrixGraph(4);
        g.addEdge(0, 1); g.addEdge(0, 2); g.addEdge(2, 3);
        System.out.println("hasEdge(0,2) = " + g.hasEdge(0, 2));
        System.out.println("hasEdge(1,3) = " + g.hasEdge(1, 3));
        System.out.println("соседи 2: " + g.neighbors(2));
    }
}
