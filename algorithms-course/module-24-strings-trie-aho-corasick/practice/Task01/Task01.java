/**
 * Задача 01 — Тема 24: Trie — insert, search, startsWith
 *
 * ЗАДАНИЕ:
 *   Реализуйте Trie (файл Trie.java) для слов из 'a'..'z' с операциями insert,
 *   search (точное слово) и startsWith (есть ли слово с таким префиксом).
 *
 * ПРИМЕР / ВЫВОД:
 *   insert "cat","car","card"
 *   search("car")      -> true
 *   search("ca")       -> false  (это префикс, не слово)
 *   startsWith("ca")   -> true
 *   startsWith("dog")  -> false
 *
 * ТРЕБОВАНИЯ:
 *   - все операции O(L), не зависят от размера словаря;
 *   - search возвращает true только для полного слова (флаг конца);
 *   - индекс ребёнка: c - 'a'.
 *
 * ПОДСКАЗКА:
 *   Разница search/startsWith: первый требует isWord в конечном узле, второй — нет.
 */

public class Task01 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String w : new String[]{"cat", "car", "card"}) trie.insert(w);
        System.out.println("search(car)    = " + trie.search("car"));
        System.out.println("search(ca)     = " + trie.search("ca"));
        System.out.println("startsWith(ca) = " + trie.startsWith("ca"));
        System.out.println("startsWith(dog)= " + trie.startsWith("dog"));
    }
}
