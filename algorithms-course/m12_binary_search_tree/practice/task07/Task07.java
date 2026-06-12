package m12_binary_search_tree.practice.task07;

/**
 * Задача 07 (мини-проект Data-Cruncher) — Тема 12: индекс на BST
 *
 * ЗАДАНИЕ:
 *   Добавьте в Data-Cruncher BstIndex (файл BstIndex.java) — индекс записей
 *   по числовому ключу на основе BST. Поддержите put(key, payload),
 *   get(key) и rangeAll() (все записи по возрастанию ключа через in-order).
 *
 * ПРИМЕР / ВЫВОД:
 *   put(50,"A"); put(30,"B"); put(70,"C"); put(30,"B2")  // обновление
 *   get(30) -> "B2"
 *   get(99) -> null
 *   rangeAll() -> [B2, A, C]   (по возрастанию ключей 30,50,70)
 *
 * ТРЕБОВАНИЯ:
 *   - put обновляет payload при повторном ключе;
 *   - get — O(h); rangeAll — отсортировано по ключу (in-order);
 *   - НЕ использовать java.util.TreeMap — это и есть учебная замена ему.
 *
 * ПОДСКАЗКА:
 *   rangeAll опирается на свойство: in-order BST идёт по возрастанию ключей.
 */

public class Task07 {
    public static void main(String[] args) {
        BstIndex idx = new BstIndex();
        idx.put(50, "A"); idx.put(30, "B"); idx.put(70, "C"); idx.put(30, "B2");
        System.out.println("get(30) = " + idx.get(30));
        System.out.println("get(99) = " + idx.get(99));
        System.out.println("rangeAll = " + idx.rangeAll());
    }
}
