/**
 * Задача 02 — Тема 13: Левый и правый повороты
 *
 * ЗАДАНИЕ:
 *   Реализуйте две базовые операции балансировки — rotateLeft и rotateRight —
 *   изолированно, и проверьте, что они СОХРАНЯЮТ инвариант BST (in-order
 *   до и после поворота одинаков), но меняют структуру/высоту.
 *
 * ПРИМЕР / ВЫВОД:
 *   Дерево:   y(2)            После rotateRight(y) корень становится x:
 *            /  \                       x
 *          x(1)  C                     / \
 *         /  \                       ...  y
 *   in-order до и после совпадает (порядок сохранён).
 *
 * ТРЕБОВАНИЯ:
 *   - rotateRight(y): x=y.left; y.left=x.right; x.right=y; вернуть x;
 *   - rotateLeft(x): симметрично;
 *   - после поворота обновить высоты (нижний узел, затем верхний);
 *   - in-order последовательность не меняется (проверка корректности).
 *
 * ПОДСКАЗКА:
 *   Поворот — это локальная перестановка трёх ссылок + перепривязка одного поддерева.
 */

import java.util.ArrayList;
import java.util.List;

public class Task02 {

    static class Node {
        int value, height = 1; Node left, right;
        Node(int value) { this.value = value; }
    }

    static int height(Node n) { return n == null ? 0 : n.height; }
    static void updateHeight(Node n) {
        if (n != null) n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    static Node rotateRight(Node y) {
        // TODO: правый поворот, обновить высоты, вернуть новый корень
        return y;
    }
    static Node rotateLeft(Node x) {
        // TODO: левый поворот, обновить высоты, вернуть новый корень
        return x;
    }

    static List<Integer> inorder(Node n) {
        List<Integer> out = new ArrayList<>();
        if (n != null) { out.addAll(inorder(n.left)); out.add(n.value); out.addAll(inorder(n.right)); }
        return out;
    }

    public static void main(String[] args) {
        // строим перекошенное вправо: 1 -> 2 -> 3
        Node a = new Node(1); a.right = new Node(2); a.right.right = new Node(3);
        System.out.println("in-order до:    " + inorder(a));
        Node b = rotateLeft(a);
        System.out.println("in-order после: " + inorder(b) + " (должно совпасть)");
    }
}
