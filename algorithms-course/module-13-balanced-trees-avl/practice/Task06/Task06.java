/**
 * Задача 06 — Тема 13: Простой NavigableSet на основе AVL
 *
 * ЗАДАНИЕ:
 *   Реализуйте AvlSet (файл AvlSet.java) — множество на AVL с операциями
 *   add, contains и НАВИГАЦИОННЫМИ запросами floor (наибольший <= x) и
 *   ceiling (наименьший >= x), которые естественны для сбалансированного BST.
 *
 * ПРИМЕР / ВЫВОД:
 *   add 10, 20, 30, 40
 *   floor(25)   -> 20
 *   ceiling(25) -> 30
 *   floor(40)   -> 40
 *   ceiling(5)  -> 10
 *   floor(5)    -> null
 *
 * ТРЕБОВАНИЯ:
 *   - дерево остаётся сбалансированным (balance уже реализован);
 *   - floor/ceiling — O(log n), без полного обхода;
 *   - возвращать null, когда подходящего элемента нет.
 *
 * ПОДСКАЗКА:
 *   floor(x): если x==node — это ответ; если x<node — идти влево; если x>node —
 *   кандидат=node, идти вправо (вдруг найдём больший, но всё ещё <= x).
 */

public class Task06 {
    public static void main(String[] args) {
        AvlSet set = new AvlSet();
        for (int v : new int[]{10, 20, 30, 40}) set.add(v);
        System.out.println("floor(25)   = " + set.floor(25));
        System.out.println("ceiling(25) = " + set.ceiling(25));
        System.out.println("floor(40)   = " + set.floor(40));
        System.out.println("ceiling(5)  = " + set.ceiling(5));
        System.out.println("floor(5)    = " + set.floor(5));
    }
}
