package m24_strings_trie_aho_corasick.practice;

/**
 * Задача 03 — Тема 24: Автодополнение — все слова с заданным префиксом
 *
 * ЗАДАНИЕ:
 *   Реализуйте автодополнение: по префиксу верните ВСЕ слова бора, начинающиеся
 *   с него (в любом порядке или отсортированные). Дойдите по префиксу до узла,
 *   затем обойдите его поддерево (DFS), собирая полные слова.
 *
 * ПРИМЕР / ВЫВОД:
 *   insert "cat","car","card","care","dog"
 *   autocomplete("car") -> [car, card, care]
 *   autocomplete("do")  -> [dog]
 *   autocomplete("x")   -> []
 *
 * ТРЕБОВАНИЯ:
 *   - дойти по префиксу; если узла нет — пустой список;
 *   - DFS по поддереву, накапливая символы пути; на isWord — добавить слово;
 *   - префикс включается в результат, если он сам — слово.
 *
 * ПОДСКАЗКА:
 *   Ведите StringBuilder с текущим путём (префикс + спуск); на isWord добавляйте в результат.
 */

import java.util.ArrayList;
import java.util.List;

public class Task03 {

    static class Node { Node[] children = new Node[26]; boolean isWord; }

    static class Trie {
        final Node root = new Node();
        void insert(String w) {
            Node cur = root;
            for (int i = 0; i < w.length(); i++) {
                int c = w.charAt(i) - 'a';
                if (cur.children[c] == null) cur.children[c] = new Node();
                cur = cur.children[c];
            }
            cur.isWord = true;
        }
        List<String> autocomplete(String prefix) {
            List<String> result = new ArrayList<>();
            // TODO: дойти до узла префикса; DFS-сбор всех слов поддерева
            return result;
        }
        // TODO: вспомогательный dfs(Node node, StringBuilder path, List<String> out)
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        for (String w : new String[]{"cat", "car", "card", "care", "dog"}) t.insert(w);
        System.out.println(t.autocomplete("car"));   // [car, card, care]
        System.out.println(t.autocomplete("do"));     // [dog]
        System.out.println(t.autocomplete("x"));      // []
    }
}
