package m12_binary_search_tree.practice;

/**
 * Задача 05 — Тема 12: Минимум и максимум в BST
 *
 * ЗАДАНИЕ:
 *   Найдите минимальное и максимальное значения в BST за O(h), пользуясь
 *   инвариантом: минимум — самый ЛЕВЫЙ узел, максимум — самый ПРАВЫЙ.
 *
 * ПРИМЕР / ВЫВОД (для 8,3,10,1,6,14):
 *   min = 1
 *   max = 14
 *
 * ТРЕБОВАНИЯ:
 *   - min: спускаться по left, пока есть; max: по right;
 *   - пустое дерево -> исключение;
 *   - O(h), без полного обхода.
 *
 * ПОДСКАЗКА:
 *   Минимум — иди влево до упора; максимум — вправо до упора.
 */

public class Task05 {

    static class Node {
        int value; Node left, right;
        Node(int value) { this.value = value; }
    }

    static Node insert(Node root, int v) {
        if (root == null) return new Node(v);
        if (v < root.value) root.left = insert(root.left, v);
        else if (v > root.value) root.right = insert(root.right, v);
        return root;
    }

    static int min(Node root) {
        // TODO: спуск по left
        return 0;
    }
    static int max(Node root) {
        // TODO: спуск по right
        return 0;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) root = insert(root, v);
        System.out.println("min = " + min(root));
        System.out.println("max = " + max(root));
    }
}
