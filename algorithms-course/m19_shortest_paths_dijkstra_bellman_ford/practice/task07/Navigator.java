package m19_shortest_paths_dijkstra_bellman_ford.practice.task07;

/**
 * Модуль Data-Cruncher: навигация по графу дорог (тема 17) с весами.
 * Использует Дейкстру: находит кратчайшее по сумме весов (расстояние/время)
 * расстояние и сам маршрут между узлами.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Navigator {

    public record Edge(int to, int weight) {}

    private final int n;
    private final List<List<Edge>> adj;

    public Navigator(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    public void addRoad(int u, int v, int w) { adj.get(u).add(new Edge(v, w)); adj.get(v).add(new Edge(u, w)); }

    /** Кратчайшее расстояние от src до всех вершин (Дейкстра). */
    public int[] distancesFrom(int src) {
        // TODO: Дейкстра с PriorityQueue
        return new int[n];
    }

    /** Маршрут (список узлов) от src до dst; пустой, если недостижим. */
    public List<Integer> route(int src, int dst) {
        // TODO: Дейкстра с prev[]; восстановить маршрут
        return new ArrayList<>();
    }
}
