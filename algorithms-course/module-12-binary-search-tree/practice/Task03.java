/**
 * Задача 03 — Тема 12: Итеративный in-order обход через стек
 *
 * ЗАДАНИЕ:
 *   Реализуйте in-order обход БЕЗ рекурсии, используя явный стек. Это важно
 *   на вырожденных деревьях, где рекурсия может переполнить стек вызовов.
 *
 * ПРИМЕР / ВЫВОД (для 8,3,10,1,6,14):
 *   inorder (итеративно): [1, 3, 6, 8, 10, 14]
 *
 * ТРЕБОВАНИЯ:
 *   - использовать java.util.Deque как стек, без рекурсии;
 *   - результат — отсортированная последовательность;
 *   - алгоритм: спускаться влево, складывая узлы в стек; затем поп — visit —
 *     перейти в правое поддерево.
 *
 * ПОДСКАЗКА:
 *   cur=root; while(cur!=null || !stack.isEmpty()){ while(cur!=null){stack.push(cur); cur=cur.left;}
 *     cur=stack.pop(); visit(cur); cur=cur.right; }
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task03 {

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

    static List<Integer> inorderIterative(Node root) {
        List<Integer> out = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        // TODO: итеративный in-order
        return out;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) root = insert(root, v);
        System.out.println(inorderIterative(root));
    }
}
