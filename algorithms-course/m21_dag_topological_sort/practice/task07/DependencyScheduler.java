package m21_dag_topological_sort.practice.task07;

/**
 * Модуль Data-Cruncher: планировщик задач с зависимостями (DAG).
 * Задачи зависят друг от друга (ребро a->b: a должна выполниться до b).
 * Возвращает корректный порядок выполнения (топосортировка) и обнаруживает
 * циклические зависимости (невозможный план).
 */
import java.util.ArrayList;
import java.util.List;

public class DependencyScheduler {

    private final int n;
    private final List<List<Integer>> deps;   // deps.get(a) -> задачи, зависящие от a

    public DependencyScheduler(int n) {
        this.n = n;
        deps = new ArrayList<>();
        for (int i = 0; i < n; i++) deps.add(new ArrayList<>());
    }

    /** Задача 'after' может начаться только после 'before'. */
    public void addDependency(int before, int after) {
        // TODO: deps.get(before).add(after)
    }

    /** Корректный порядок выполнения (топосортировка, Кан). Пустой список, если есть цикл. */
    public List<Integer> executionOrder() {
        // TODO: Кан; если обработаны не все задачи — цикл -> вернуть пустой список
        return new ArrayList<>();
    }

    /** Есть ли циклическая (невыполнимая) зависимость. */
    public boolean hasCycle() {
        // TODO: executionOrder().size() != n
        return false;
    }
}
