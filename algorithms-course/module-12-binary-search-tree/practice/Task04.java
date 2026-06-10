/**
 * Задача 04 — Тема 12: Level-order обход (BFS) через очередь
 *
 * ЗАДАНИЕ:
 *   Обойдите дерево по УРОВНЯМ (сверху вниз, слева направо), используя очередь.
 *   Это поиск в ширину (BFS) на дереве. Соберите значения по уровням.
 *
 * ПРИМЕР / ВЫВОД (для 8,3,10,1,6,14):
 *   уровни:
 *   [8]
 *   [3, 10]
 *   [1, 6, 14]
 *
 * ТРЕБОВАНИЯ:
 *   - использовать java.util.Queue (ArrayDeque);
 *   - вернуть List<List<Integer>> — список уровней;
 *   - на каждой итерации обрабатывать ровно текущий уровень (по размеру очереди).
 *
 * ПОДСКАЗКА:
 *   Положить корень; пока очередь не пуста: levelSize=queue.size(); снять levelSize узлов,
 *   собрать их значения в уровень, добавить их детей в очередь.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Task04 {

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

    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        // TODO: BFS по уровням через очередь
        return levels;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) root = insert(root, v);
        levelOrder(root).forEach(System.out::println);
    }
}
