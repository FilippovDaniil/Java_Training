/**
 * Задача 05 — Тема 17: Транспонирование орграфа
 *
 * ЗАДАНИЕ:
 *   Постройте транспонированный орграф: для каждого ребра u->v в исходном
 *   создайте ребро v->u в результате. Транспонирование нужно, например, при
 *   поиске компонент сильной связности (алгоритм Косарайю).
 *
 * ПРИМЕР / ВЫВОД:
 *   исходный: 0->1, 0->2, 2->3
 *   транспонированный: 1->0, 2->0, 3->2
 *
 * ТРЕБОВАНИЯ:
 *   - вход — список смежности орграфа; результат — новый список смежности;
 *   - не менять исходный граф;
 *   - O(V + E).
 *
 * ПОДСКАЗКА:
 *   Для каждой вершины u и каждого её соседа v: добавить u в список v результата.
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {

    static List<List<Integer>> transpose(List<List<Integer>> adj) {
        int n = adj.size();
        List<List<Integer>> t = new ArrayList<>();
        for (int i = 0; i < n; i++) t.add(new ArrayList<>());
        // TODO: для каждого ребра u->v добавить v->u в t
        return t;
    }

    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(0).add(2); adj.get(2).add(3);
        List<List<Integer>> t = transpose(adj);
        for (int u = 0; u < t.size(); u++) System.out.println(u + " -> " + t.get(u));
    }
}
