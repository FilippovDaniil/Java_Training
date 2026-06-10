/**
 * Задача 03 — Тема 20: Вес минимального остова и список его рёбер
 *
 * ЗАДАНИЕ:
 *   Постройте MST (любым из алгоритмов) и верните НЕ ТОЛЬКО суммарный вес, но
 *   и сами выбранные рёбра. Проверьте, что рёбер ровно V-1 и они связывают
 *   все вершины.
 *
 * ПРИМЕР / ВЫВОД:
 *   рёбра: (0-1,3),(0-2,1),(1-3,2),(2-3,4),(1-2,2)
 *   MST рёбра: [(0-2,1), (1-2,2), (1-3,2)]  (3 ребра = V-1)
 *   суммарный вес: 5
 *
 * ТРЕБОВАНИЯ:
 *   - вернуть список рёбер MST и суммарный вес;
 *   - проверить: число рёбер == V-1 (для связного графа);
 *   - удобно на базе Крускала (легко собирать выбранные рёбра).
 *
 * ПОДСКАЗКА:
 *   Каждый раз, когда union успешен, добавляйте ребро в результат.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task03 {

    static class DSU {
        int[] p; DSU(int n){ p=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x){ return p[x]==x ? x : (p[x]=find(p[x])); }
        boolean union(int a,int b){ int ra=find(a),rb=find(b); if(ra==rb) return false; p[ra]=rb; return true; }
    }

    static List<int[]> mstEdges(int n, int[][] edges) {
        List<int[]> result = new ArrayList<>();
        // TODO: Крускал; собирать выбранные рёбра в result
        return result;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,3},{0,2,1},{1,3,2},{2,3,4},{1,2,2}};
        List<int[]> mst = mstEdges(4, edges);
        int total = 0;
        for (int[] e : mst) { System.out.println(Arrays.toString(e)); total += e[2]; }
        System.out.println("рёбер: " + mst.size() + " (V-1=3), вес: " + total);
    }
}
