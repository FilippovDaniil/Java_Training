import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Ищет цикл в графе зависимостей (DFS).
class CycleDetector {
    public boolean hasCycle(Map<String, Set<String>> deps) {
        // TODO: для каждой вершины запустить DFS;
        //       visited — полностью обработанные; inStack — вершины текущего пути;
        //       если в DFS дошли до вершины, уже находящейся в inStack → есть цикл.
        //       Подсказка: вынесите рекурсивный обход в приватный вспомогательный метод.
        return false;
    }
}
