/**
 * AVL-дерево целых чисел: BST, который после вставки восстанавливает баланс
 * поворотами, удерживая высоту ~log n.
 */
public class AvlTree {

    static class Node {
        int value, height = 1;     // высота листа = 1
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;

    int height(Node n) { return n == null ? 0 : n.height; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    void updateHeight(Node n) {
        // TODO: n.height = 1 + max(height(left), height(right))
    }

    Node rotateRight(Node y) {
        // TODO: правый поворот; обновить высоты y, затем нового корня; вернуть новый корень
        return y;
    }
    Node rotateLeft(Node x) {
        // TODO: левый поворот; обновить высоты; вернуть новый корень
        return x;
    }

    public void insert(int value) {
        // TODO: root = insert(root, value)
    }

    private Node insert(Node node, int value) {
        // TODO: 1) обычная вставка BST; 2) updateHeight; 3) balance;
        //       4) случаи LL/RR/LR/RL -> повороты; 5) вернуть новый корень
        return node;
    }

    /** Проверка инварианта AVL: |balance| <= 1 во всех узлах. */
    public boolean isBalanced() {
        // TODO: рекурсивно проверить |balance| <= 1 для каждого узла
        return true;
    }

    public int treeHeight() { return height(root); }
}
