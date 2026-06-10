/**
 * Задача 01 — Тема 13: AVL-дерево — вставка с проверкой баланса
 *
 * ЗАДАНИЕ:
 *   Реализуйте AvlTree (файл AvlTree.java): вставка с автоматической
 *   балансировкой (повороты LL/RR/LR/RL) и метод isBalanced() для проверки
 *   инварианта |balance| <= 1.
 *
 * ПРИМЕР / ВЫВОД:
 *   вставляем 1,2,3,4,5,6,7 (по возрастанию — худший случай для обычного BST)
 *   isBalanced() = true
 *   высота дерева = 3   (а у вырожденного BST была бы 7)
 *
 * ТРЕБОВАНИЯ:
 *   - после вставки дерево сбалансировано (isBalanced() == true);
 *   - высота для n узлов ~ log n (а не n);
 *   - реализованы updateHeight, rotateLeft, rotateRight.
 *
 * ПОДСКАЗКА:
 *   Вставка 1..7 по порядку в обычный BST даёт «бамбук» высоты 7; AVL держит ~3.
 */

public class Task01 {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        for (int v = 1; v <= 7; v++) tree.insert(v);
        System.out.println("isBalanced = " + tree.isBalanced());
        System.out.println("высота = " + tree.treeHeight());
    }
}
