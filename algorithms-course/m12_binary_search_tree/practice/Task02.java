package m12_binary_search_tree.practice;

/**
 * Задача 02 — Тема 12: Три рекурсивных обхода (pre/in/post-order)
 *
 * ЗАДАНИЕ:
 *   Реализуйте три рекурсивных обхода BST, добавляя значения в список:
 *     - preorder  (узел, лево, право);
 *     - inorder   (лево, узел, право) — даёт ОТСОРТИРОВАННЫЙ порядок;
 *     - postorder (лево, право, узел).
 *   insert уже дан — стройте дерево им.
 *
 * ПРИМЕР / ВЫВОД (для 8,3,10,1,6,14):
 *   preorder:  [8, 3, 1, 6, 10, 14]
 *   inorder:   [1, 3, 6, 8, 10, 14]   (отсортировано!)
 *   postorder: [1, 6, 3, 14, 10, 8]
 *
 * ТРЕБОВАНИЯ:
 *   - три отдельных рекурсивных метода;
 *   - результат собирается в List<Integer> в правильном порядке;
 *   - inorder обязан давать отсортированную последовательность.
 *
 * ПОДСКАЗКА:
 *   Отличие обходов — только в МЕСТЕ, где вы добавляете value относительно рекурсий.
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    static class Node {
        int value; Node left, right;
        Node(int value) { this.value = value; }
    }

    // дано: вставка в BST
    static Node insert(Node root, int v) {
        if (root == null) return new Node(v);
        if (v < root.value) root.left = insert(root.left, v);
        else if (v > root.value) root.right = insert(root.right, v);
        return root;
    }

    static List<Integer> preorder(Node root) {
        List<Integer> out = new ArrayList<>();
        // TODO
        return out;
    }
    static List<Integer> inorder(Node root) {
        List<Integer> out = new ArrayList<>();
        // TODO
        return out;
    }
    static List<Integer> postorder(Node root) {
        List<Integer> out = new ArrayList<>();
        // TODO
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) root = insert(root, v);
        System.out.println("preorder:  " + preorder(root));
        System.out.println("inorder:   " + inorder(root));
        System.out.println("postorder: " + postorder(root));
    }
}
