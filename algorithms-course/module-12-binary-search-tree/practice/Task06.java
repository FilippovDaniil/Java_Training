/**
 * Задача 06 — Тема 12: Удаление узла (включая случай двух детей)
 *
 * ЗАДАНИЕ:
 *   Реализуйте удаление значения из BST, корректно обрабатывая три случая:
 *     1) лист (нет детей) — отвязать;
 *     2) один ребёнок — заменить узел ребёнком;
 *     3) два ребёнка — заменить значением ПРЕЕМНИКА (минимум правого поддерева),
 *        затем удалить преемника.
 *   После удаления инвариант BST должен сохраняться (проверьте in-order — он
 *   остаётся отсортированным).
 *
 * ПРИМЕР / ВЫВОД (для 8,3,10,1,6,14):
 *   inorder до:     [1, 3, 6, 8, 10, 14]
 *   delete(8) (два ребёнка) ->
 *   inorder после:  [1, 3, 6, 10, 14]
 *
 * ТРЕБОВАНИЯ:
 *   - рекурсивное удаление, возвращающее обновлённое поддерево;
 *   - случай двух детей через преемника (min справа);
 *   - in-order после удаления остаётся отсортированным.
 *
 * ПОДСКАЗКА:
 *   delete(node, v): спуститься к узлу; для двух детей: succ=min(node.right);
 *   node.value=succ; node.right=delete(node.right, succ).
 */

import java.util.ArrayList;
import java.util.List;

public class Task06 {

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

    static Node delete(Node root, int v) {
        // TODO: три случая удаления; вернуть обновлённое поддерево
        return root;
    }

    static List<Integer> inorder(Node n) {
        List<Integer> out = new ArrayList<>();
        if (n != null) { out.addAll(inorder(n.left)); out.add(n.value); out.addAll(inorder(n.right)); }
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) root = insert(root, v);
        System.out.println("inorder до:    " + inorder(root));
        root = delete(root, 8);
        System.out.println("inorder после: " + inorder(root));
    }
}
