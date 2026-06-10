/**
 * Модуль Data-Cruncher: индекс на AVL-дереве — замена BstIndex (тема 12)
 * ради ГАРАНТИРОВАННОЙ логарифмической производительности даже при
 * вставке отсортированных ключей (где обычный BST вырождался в список).
 */
public class AvlIndex {

    static class Node {
        int key; String payload; int height = 1; Node left, right;
        Node(int key, String payload) { this.key = key; this.payload = payload; }
    }

    private Node root;

    int height(Node n) { return n == null ? 0 : n.height; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    void updateHeight(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }
    Node rotateRight(Node y){ Node x=y.left; y.left=x.right; x.right=y; updateHeight(y); updateHeight(x); return x; }
    Node rotateLeft(Node x){ Node y=x.right; x.right=y.left; y.left=x; updateHeight(x); updateHeight(y); return y; }

    Node rebalance(Node n) {
        // TODO: updateHeight + повороты по фактору баланса (LL/RR/LR/RL)
        return n;
    }

    /** Вставить/обновить запись. Гарантированно O(log n). */
    public void put(int key, String payload) {
        // TODO: root = put(root, key, payload) с ребалансировкой
    }

    /** Найти payload по ключу. O(log n). */
    public String get(int key) {
        // TODO: спуск по дереву
        return null;
    }

    /** Высота дерева — для демонстрации, что она ~log n. */
    public int treeHeight() { return height(root); }
}
