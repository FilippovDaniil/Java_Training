package m13_balanced_trees_avl.practice;

/**
 * Задача 04 — Тема 13: Удаление в AVL (с ребалансировкой)
 *
 * ЗАДАНИЕ:
 *   Дано рабочее AVL-дерево (insert и повороты уже реализованы). Добавьте
 *   удаление delete(value): обычное удаление BST (три случая, как в теме 12),
 *   затем на обратном пути — обновление высот и повороты для восстановления
 *   баланса. После серии удалений дерево остаётся сбалансированным.
 *
 * ПРИМЕР / ВЫВОД:
 *   вставили 1..10, удалили 1,2,3,4
 *   isBalanced() = true
 *   in-order = [5, 6, 7, 8, 9, 10]
 *
 * ТРЕБОВАНИЯ:
 *   - три случая удаления BST (лист / один ребёнок / два ребёнка через преемника);
 *   - после удаления — updateHeight + ребалансировка (LL/RR/LR/RL);
 *   - isBalanced() == true после всех удалений.
 *
 * ПОДСКАЗКА:
 *   Ребалансировку оформите отдельным методом rebalance(node) и вызывайте его
 *   на выходе из insert И delete — код переиспользуется.
 */

import java.util.ArrayList;
import java.util.List;

public class Task04 {

    static class Node {
        int value, height = 1; Node left, right;
        Node(int value) { this.value = value; }
    }

    static int height(Node n) { return n == null ? 0 : n.height; }
    static int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    static void updateHeight(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }

    static Node rotateRight(Node y) {
        Node x = y.left; y.left = x.right; x.right = y; updateHeight(y); updateHeight(x); return x;
    }
    static Node rotateLeft(Node x) {
        Node y = x.right; x.right = y.left; y.left = x; updateHeight(x); updateHeight(y); return y;
    }

    static Node rebalance(Node n) {
        updateHeight(n);
        int b = balance(n);
        if (b > 1) { if (balance(n.left) < 0) n.left = rotateLeft(n.left); return rotateRight(n); }
        if (b < -1) { if (balance(n.right) > 0) n.right = rotateRight(n.right); return rotateLeft(n); }
        return n;
    }

    // дано: вставка в AVL
    static Node insert(Node node, int v) {
        if (node == null) return new Node(v);
        if (v < node.value) node.left = insert(node.left, v);
        else if (v > node.value) node.right = insert(node.right, v);
        else return node;
        return rebalance(node);
    }

    static Node delete(Node node, int v) {
        // TODO: обычное удаление BST (3 случая), затем return rebalance(node)
        //       (не забудьте: если node стал null — вернуть null до rebalance)
        return node;
    }

    static int minValue(Node n) { while (n.left != null) n = n.left; return n.value; }

    static List<Integer> inorder(Node n) {
        List<Integer> out = new ArrayList<>();
        if (n != null) { out.addAll(inorder(n.left)); out.add(n.value); out.addAll(inorder(n.right)); }
        return out;
    }
    static boolean isBalanced(Node n) {
        if (n == null) return true;
        return Math.abs(balance(n)) <= 1 && isBalanced(n.left) && isBalanced(n.right);
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v = 1; v <= 10; v++) root = insert(root, v);
        for (int v = 1; v <= 4; v++) root = delete(root, v);
        System.out.println("isBalanced = " + isBalanced(root));
        System.out.println("in-order = " + inorder(root));
    }
}
