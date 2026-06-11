package m16_bitset_disjoint_set_union.practice;

/**
 * Задача 06 — Тема 16: «Друзья и враги» (DSU с двумя отношениями)
 *
 * ЗАДАНИЕ:
 *   Классическая задача. Есть n существ. Поступают утверждения двух видов:
 *     - FRIEND a b  — a и b друзья (в одной группе);
 *     - ENEMY  a b  — a и b враги (в разных группах).
 *   Приём: расширенный DSU на 2n элементов: для каждого i есть «i сам» и
 *   «i-враг» (i + n). FRIEND(a,b): union(a,b) и union(a+n,b+n).
 *   ENEMY(a,b): union(a, b+n) и union(a+n, b) («враг моего врага — друг»).
 *   Затем умейте отвечать: areFriends(a,b), areEnemies(a,b).
 *
 * ПРИМЕР / ВЫВОД:
 *   n=4; FRIEND(0,1); ENEMY(1,2); ENEMY(2,3)
 *   areFriends(0,1) -> true
 *   areEnemies(0,2) -> true   (0 друг 1, 1 враг 2)
 *   areFriends(1,3) -> true   (1 враг 2, 2 враг 3 -> 1 и 3 друзья)
 *
 * ТРЕБОВАНИЯ:
 *   - DSU размера 2n (элемент i и его «антипод» i+n);
 *   - areFriends(a,b) = connected(a,b);
 *   - areEnemies(a,b) = connected(a, b+n).
 *
 * ПОДСКАЗКА:
 *   Это та же идея, что «2-раскраска»/проверка двудольности, но через Union-Find.
 */

public class Task06 {

    static class DSU {
        int[] p;
        DSU(int n){ p=new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x){ return p[x]==x ? x : (p[x]=find(p[x])); }
        void union(int x,int y){ p[find(x)]=find(y); }
        boolean connected(int x,int y){ return find(x)==find(y); }
    }

    static int n;
    static DSU dsu;

    static void friend(int a, int b) {
        // TODO: union(a,b) и union(a+n, b+n)
    }
    static void enemy(int a, int b) {
        // TODO: union(a, b+n) и union(a+n, b)
    }
    static boolean areFriends(int a, int b) {
        // TODO: connected(a, b)
        return false;
    }
    static boolean areEnemies(int a, int b) {
        // TODO: connected(a, b+n)
        return false;
    }

    public static void main(String[] args) {
        n = 4; dsu = new DSU(2 * n);
        friend(0, 1); enemy(1, 2); enemy(2, 3);
        System.out.println("areFriends(0,1) = " + areFriends(0, 1));
        System.out.println("areEnemies(0,2) = " + areEnemies(0, 2));
        System.out.println("areFriends(1,3) = " + areFriends(1, 3));
    }
}
