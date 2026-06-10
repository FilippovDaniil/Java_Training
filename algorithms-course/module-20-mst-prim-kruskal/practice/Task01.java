/**
 * Задача 01 — Тема 20: Алгоритм Крускала с DSU
 *
 * ЗАДАНИЕ:
 *   Постройте минимальное остовное дерево алгоритмом Крускала: отсортируйте
 *   рёбра по весу и добавляйте каждое, если оно соединяет РАЗНЫЕ компоненты
 *   (DSU). Верните суммарный вес MST и список выбранных рёбер.
 *
 * ПРИМЕР / ВЫВОД:
 *   рёбра: (0-1,3),(0-2,1),(1-3,2),(2-3,4),(1-2,2)
 *   вес MST = 5   (например рёбра 0-2(1), 1-2(2), 1-3(2))
 *
 * ТРЕБОВАНИЯ:
 *   - сортировка рёбер по весу;
 *   - DSU с find/union (можно со сжатием пути); добавлять ребро, если find разные;
 *   - остановка после V-1 рёбер; вернуть суммарный вес.
 *
 * ПОДСКАЗКА:
 *   Edge {u, v, w}; Arrays.sort по w; для каждого: if find(u)!=find(v) -> union, total+=w.
 */

import java.util.Arrays;

public class Task01 {

    static class DSU {
        int[] p;
        DSU(int n){ p=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x){ return p[x]==x ? x : (p[x]=find(p[x])); }
        boolean union(int a,int b){ int ra=find(a),rb=find(b); if(ra==rb) return false; p[ra]=rb; return true; }
    }

    static int kruskal(int n, int[][] edges) {
        // TODO: отсортировать edges по весу; DSU; суммировать веса рёбер без цикла
        return 0;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,3},{0,2,1},{1,3,2},{2,3,4},{1,2,2}};
        System.out.println("вес MST = " + kruskal(4, edges));   // 5
    }
}
