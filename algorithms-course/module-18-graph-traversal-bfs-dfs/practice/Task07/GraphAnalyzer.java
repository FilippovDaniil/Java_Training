/**
 * Модуль Data-Cruncher: анализ связности и кратчайших путей в графе данных.
 * Работает с неориентированным графом (список смежности):
 *  - countComponents — число компонент связности;
 *  - shortestHops — кратчайший путь (по числу рёбер) между двумя узлами (BFS).
 */
import java.util.ArrayList;
import java.util.List;

public class GraphAnalyzer {

    private final int n;
    private final List<List<Integer>> adj;

    public GraphAnalyzer(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) { adj.get(u).add(v); adj.get(v).add(u); }

    /** Число компонент связности (BFS/DFS от каждой непосещённой). */
    public int countComponents() {
        // TODO
        return 0;
    }

    /** Кратчайший путь (список вершин) между s и t через BFS; пусто, если нет пути. */
    public List<Integer> shortestHops(int s, int t) {
        // TODO: BFS + восстановление пути по parent
        return new ArrayList<>();
    }
}
