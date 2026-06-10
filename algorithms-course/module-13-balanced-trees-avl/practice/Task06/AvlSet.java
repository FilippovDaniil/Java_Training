/**
 * Упрощённый NavigableSet на основе AVL-дерева.
 * Помимо add/contains поддерживает «навигационные» запросы floor/ceiling,
 * которые естественны для сбалансированного BST.
 */
public class AvlSet {

    static class Node {
        int value, height = 1; Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;

    int height(Node n) { return n == null ? 0 : n.height; }
    int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    void updateHeight(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }
    Node rotateRight(Node y){ Node x=y.left; y.left=x.right; x.right=y; updateHeight(y); updateHeight(x); return x; }
    Node rotateLeft(Node x){ Node y=x.right; x.right=y.left; y.left=x; updateHeight(x); updateHeight(y); return y; }
    Node rebalance(Node n){
        updateHeight(n); int b=balance(n);
        if(b>1){ if(balance(n.left)<0) n.left=rotateLeft(n.left); return rotateRight(n);}
        if(b<-1){ if(balance(n.right)>0) n.right=rotateRight(n.right); return rotateLeft(n);}
        return n;
    }

    public void add(int value) { root = add(root, value); }
    private Node add(Node n, int v) {
        if (n == null) return new Node(v);
        if (v < n.value) n.left = add(n.left, v);
        else if (v > n.value) n.right = add(n.right, v);
        else return n;
        return rebalance(n);
    }

    public boolean contains(int value) {
        // TODO: спуск по дереву
        return false;
    }

    /** Наибольший элемент <= value, или null если такого нет. */
    public Integer floor(int value) {
        // TODO: спускаясь, запоминать кандидата (узел со значением <= value)
        return null;
    }

    /** Наименьший элемент >= value, или null если такого нет. */
    public Integer ceiling(int value) {
        // TODO: симметрично floor
        return null;
    }
}
