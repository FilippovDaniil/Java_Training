/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 13: замена BST на AVL
 *
 * ЗАДАНИЕ:
 *   Замените индекс Data-Cruncher на AVL (файл AvlIndex.java). Покажите, что
 *   при вставке отсортированных ключей (худший случай для BST из темы 12)
 *   высота остаётся ~log n, а get по-прежнему O(log n).
 *
 * ПРИМЕР / ВЫВОД:
 *   put ключи 1..1000 по возрастанию (payload "v"+key)
 *   высота AVL-индекса = 10   (~log2(1000)); у BST было бы 1000
 *   get(500) = "v500"
 *
 * ТРЕБОВАНИЯ:
 *   - put с ребалансировкой; get за O(log n);
 *   - высота для 1000 ключей ~10 (а не 1000);
 *   - тот же контракт, что у BstIndex (тема 12), но с гарантией.
 *
 * ПОДСКАЗКА:
 *   Главное достижение темы: O(log n) НЕ зависит от порядка вставки.
 */

public class Task07 {
    public static void main(String[] args) {
        AvlIndex idx = new AvlIndex();
        for (int k = 1; k <= 1000; k++) idx.put(k, "v" + k);
        System.out.println("высота = " + idx.treeHeight());
        System.out.println("get(500) = " + idx.get(500));
    }
}
