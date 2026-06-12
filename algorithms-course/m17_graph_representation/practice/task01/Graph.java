package m17_graph_representation.practice.task01;

/**
 * Неориентированный граф на списке смежности.
 * Вершины нумеруются 0..n-1. adj.get(u) — список соседей u.
 */
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final int n;
    private final List<List<Integer>> adj;

    public Graph(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    /** Добавить НЕориентированное ребро (u,v) — в обе стороны. O(1). */
    public void addEdge(int u, int v) {
        // TODO: adj.get(u).add(v); adj.get(v).add(u);
    }

    /** Список соседей вершины u. */
    public List<Integer> neighbors(int u) {
        return adj.get(u);
    }

    public int size() { return n; }
}
