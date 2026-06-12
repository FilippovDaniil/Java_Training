package m12_binary_search_tree.practice.task01;

/**
 * Задача 01 — Тема 12: BST со вставкой и поиском
 *
 * ЗАДАНИЕ:
 *   Реализуйте BinarySearchTree (файл BinarySearchTree.java) с методами
 *   insert(value) и contains(value), соблюдая инвариант BST.
 *
 * ПРИМЕР / ВЫВОД:
 *   вставляем 8,3,10,1,6,14
 *   contains(6)  -> true
 *   contains(7)  -> false
 *
 * ТРЕБОВАНИЯ:
 *   - insert и contains — O(h), реализованы рекурсивно или итеративно;
 *   - дубликаты игнорируются (повторная вставка не меняет дерево);
 *   - НЕ использовать java.util.TreeSet/TreeMap — пишем своё.
 *
 * ПОДСКАЗКА:
 *   insert(node, v): если node==null -> new Node(v); если v<node.value -> влево; иначе вправо.
 */

public class Task01 {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) bst.insert(v);
        System.out.println("contains(6) = " + bst.contains(6));
        System.out.println("contains(7) = " + bst.contains(7));
    }
}
