/**
 * Задача 02 — Тема 24: Удаление слова и подсчёт слов с префиксом
 *
 * ЗАДАНИЕ:
 *   Расширьте Trie двумя операциями:
 *     - countWordsWithPrefix(prefix) — сколько слов в боре начинаются с префикса;
 *     - delete(word) — удалить слово (снять признак слова, обновить счётчики).
 *   Для счётчика храните в каждом узле, сколько слов проходит через него.
 *
 * ПРИМЕР / ВЫВОД:
 *   insert "cat","car","card","dog"
 *   countWordsWithPrefix("ca") -> 3
 *   delete("car")
 *   countWordsWithPrefix("ca") -> 2
 *   countWordsWithPrefix("c")  -> 2
 *
 * ТРЕБОВАНИЯ:
 *   - в каждом узле поле prefixCount (число слов через узел);
 *   - insert увеличивает prefixCount по пути; delete уменьшает;
 *   - countWordsWithPrefix = prefixCount узла, до которого ведёт префикс (или 0).
 *
 * ПОДСКАЗКА:
 *   delete только существующего слова; иначе счётчики разъедутся — проверяйте search.
 */

public class Task02 {

    static class Node {
        Node[] children = new Node[26];
        boolean isWord;
        int prefixCount;   // сколько слов проходит через этот узел
    }

    static class Trie {
        final Node root = new Node();

        void insert(String w) {
            // TODO: спускаться, prefixCount++ по пути, в конце isWord=true
        }
        boolean search(String w) {
            Node cur = root;
            for (int i = 0; i < w.length(); i++) {
                cur = cur.children[w.charAt(i) - 'a'];
                if (cur == null) return false;
            }
            return cur.isWord;
        }
        int countWordsWithPrefix(String p) {
            // TODO: дойти до узла префикса; вернуть его prefixCount (или 0)
            return 0;
        }
        void delete(String w) {
            // TODO: если search(w) — пройти по пути, prefixCount--, в конце isWord=false
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        for (String w : new String[]{"cat", "car", "card", "dog"}) t.insert(w);
        System.out.println("ca -> " + t.countWordsWithPrefix("ca"));   // 3
        t.delete("car");
        System.out.println("ca -> " + t.countWordsWithPrefix("ca"));   // 2
        System.out.println("c  -> " + t.countWordsWithPrefix("c"));    // 2
    }
}
