/**
 * Система непересекающихся множеств (DSU / Union-Find), базовая версия.
 * parent[x] — родитель x; корень ссылается сам на себя.
 */
public class DisjointSet {

    private final int[] parent;

    public DisjointSet(int n) {
        parent = new int[n];
        // TODO: makeSet для всех: parent[i] = i
    }

    /** Представитель (корень) множества элемента x. */
    public int find(int x) {
        // TODO: идти по parent, пока parent[x] != x
        return x;
    }

    /** Объединить множества, содержащие x и y. */
    public void union(int x, int y) {
        // TODO: parent[find(x)] = find(y) (если корни разные)
    }

    /** В одном ли множестве x и y. */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
