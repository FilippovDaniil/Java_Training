/**
 * Задача 05 — Тема 13: Высота AVL против обычного BST на случайных данных
 *
 * ЗАДАНИЕ:
 *   Вставьте одни и те же N значений в обычный BST и в AVL-дерево, сравните
 *   итоговые высоты. На ОТСОРТИРОВАННЫХ данных разница максимальна (BST = N,
 *   AVL ~ log N). На случайных данных BST в среднем ~1.39·log N, AVL — ещё ниже.
 *
 * ПРИМЕР / ВЫВОД:
 *   N=1000, вставка по возрастанию 0..999:
 *   высота BST = 1000   (вырождение!)
 *   высота AVL = 10      (~log2(1000))
 *   N=1000, случайные:
 *   высота BST ~ 22, высота AVL ~ 11
 *
 * ТРЕБОВАНИЯ:
 *   - bstInsert дан (обычная вставка); avlInsert реализуйте (можно из задачи 04);
 *   - сравните высоты на отсортированном и на случайном входе;
 *   - сделайте вывод о гарантии AVL против «бамбука» BST.
 *
 * ПОДСКАЗКА:
 *   Высота — max глубина. Для отсортированного входа разница нагляднее всего.
 */

public class Task05 {

    static class Node {
        int value, height = 1; Node left, right;
        Node(int value) { this.value = value; }
    }
    static int height(Node n) { return n == null ? 0 : n.height; }

    // дано: обычная вставка BST (без балансировки)
    static Node bstInsert(Node node, int v) {
        if (node == null) return new Node(v);
        if (v < node.value) node.left = bstInsert(node.left, v);
        else if (v > node.value) node.right = bstInsert(node.right, v);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    static Node avlInsert(Node node, int v) {
        // TODO: вставка с балансировкой (как в задаче 04) — обновляет height и поворачивает
        return node;
    }

    public static void main(String[] args) {
        int n = 1000;
        Node bst = null, avl = null;
        for (int v = 0; v < n; v++) { bst = bstInsert(bst, v); avl = avlInsert(avl, v); }
        System.out.println("отсортированный вход, N=" + n);
        System.out.println("высота BST = " + height(bst));
        System.out.println("высота AVL = " + height(avl));
    }
}
