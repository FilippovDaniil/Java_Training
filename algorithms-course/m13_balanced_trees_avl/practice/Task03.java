package m13_balanced_trees_avl.practice;

/**
 * Задача 03 — Тема 13: Обновление высоты и вычисление фактора баланса
 *
 * ЗАДАНИЕ:
 *   Реализуйте корректное хранение и обновление высоты узла и вычисление
 *   фактора баланса. Постройте небольшое дерево вручную, проставьте высоты
 *   снизу вверх (post-order) и выведите высоту и баланс каждого узла.
 *
 * ПРИМЕР / ВЫВОД:
 *   дерево:    8
 *             / \
 *            3   10
 *             \
 *              6
 *   узел 6: height=1, balance=0
 *   узел 3: height=2, balance=-1   (нет левого, есть правый)
 *   узел 10:height=1, balance=0
 *   узел 8: height=3, balance=+1
 *
 * ТРЕБОВАНИЯ:
 *   - height(null)=0, height листа=1;
 *   - balance(n)=height(left)-height(right);
 *   - recomputeHeights обходит дерево в post-order и проставляет n.height;
 *   - вывести (значение, height, balance) для каждого узла.
 *
 * ПОДСКАЗКА:
 *   Высоту считайте post-order: сначала дети, потом узел = 1+max(детей).
 */

public class Task03 {

    static class Node {
        int value, height; Node left, right;
        Node(int value) { this.value = value; }
    }

    static int height(Node n) { return n == null ? 0 : n.height; }
    static int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    static void recomputeHeights(Node n) {
        // TODO: post-order — сначала дети, затем n.height = 1 + max(height(left), height(right))
    }

    static void printInfo(Node n) {
        if (n == null) return;
        printInfo(n.left);
        System.out.println("узел " + n.value + ": height=" + n.height + ", balance=" + balance(n));
        printInfo(n.right);
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3); root.right = new Node(10);
        root.left.right = new Node(6);
        recomputeHeights(root);
        printInfo(root);
    }
}
