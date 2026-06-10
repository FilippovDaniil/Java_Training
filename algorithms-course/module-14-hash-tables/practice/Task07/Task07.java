/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 14: индекс на хеш-таблице
 *
 * ЗАДАНИЕ:
 *   Переделайте индекс Data-Cruncher на хеш-таблицу (файл HashIndex.java):
 *   поиск по ключу за O(1) в среднем (вместо O(log n) у дерева). Поддержите
 *   put, get и авторасширение (rehash).
 *
 * ПРИМЕР / ВЫВОД:
 *   put 100000 записей (key -> "row"+key)
 *   get(54321) -> "row54321"
 *   get(-1)    -> null
 *   size = 100000
 *
 * ТРЕБОВАНИЯ:
 *   - put обновляет значение при повторном ключе;
 *   - get — O(1) в среднем (благодаря рехешированию цепочки короткие);
 *   - сравните с BST/AVL-индексом: здесь нет упорядоченного обхода, зато быстрее доступ.
 *
 * ПОДСКАЗКА:
 *   Это «прямой» аналог java.util.HashMap для нашего индекса.
 */

public class Task07 {
    public static void main(String[] args) {
        HashIndex idx = new HashIndex();
        for (int k = 0; k < 100_000; k++) idx.put(k, "row" + k);
        System.out.println("get(54321) = " + idx.get(54321));
        System.out.println("get(-1)    = " + idx.get(-1));
        System.out.println("size = " + idx.size());
    }
}
