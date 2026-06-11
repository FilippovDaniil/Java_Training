package m12_binary_search_tree.practice.task01;

/**
 * Двоичное дерево поиска (BST) целых чисел.
 * Инвариант: для каждого узла всё в левом поддереве < узла < всё в правом.
 */
public class BinarySearchTree {

    static class Node {
        int value; Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;

    /** Вставить значение (дубликаты игнорируются). O(h). */
    public void insert(int value) {
        // TODO: root = insert(root, value) — рекурсивный помощник
    }

    // TODO: private Node insert(Node node, int value)

    /** Содержит ли дерево значение. O(h). */
    public boolean contains(int value) {
        // TODO: спускаться влево/вправо, пока не найдём или не упрёмся в null
        return false;
    }

    public boolean isEmpty() { return root == null; }
}
