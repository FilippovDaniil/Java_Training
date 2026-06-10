/**
 * Модуль Data-Cruncher: граф дорог (взвешенный, неориентированный).
 * Вершины — города/узлы, рёбра — дороги с весом (расстояние/время).
 * Хранится списком смежности из Edge. Будет основой навигации (темы 18–20).
 */
import java.util.ArrayList;
import java.util.List;

public class RoadGraph {

    public record Edge(int to, int weight) {}

    private final int n;
    private final List<List<Edge>> adj;

    public RoadGraph(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    /** Добавить дорогу (неориентированную) между u и v с весом w. */
    public void addRoad(int u, int v, int w) {
        // TODO: adj.get(u).add(new Edge(v,w)); adj.get(v).add(new Edge(u,w));
    }

    public List<Edge> roadsFrom(int u) { return adj.get(u); }
    public int size() { return n; }

    /** Загрузить из текста: строки "u v w". */
    public static RoadGraph fromText(int n, String text) {
        RoadGraph g = new RoadGraph(n);
        // TODO: разобрать строки "u v w" и addRoad
        return g;
    }
}
