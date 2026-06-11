package m24_strings_trie_aho_corasick.practice.task01;

/**
 * Префиксное дерево (бор) для строк из букв 'a'..'z'.
 * Каждый узел: массив из 26 детей + флаг конца слова.
 */
public class Trie {

    static class Node {
        Node[] children = new Node[26];
        boolean isWord;
    }

    private final Node root = new Node();

    /** Вставить слово. O(L). */
    public void insert(String word) {
        // TODO: спускаться по символам, создавая узлы; в конце isWord=true
    }

    /** Есть ли ТОЧНО такое слово. O(L). */
    public boolean search(String word) {
        // TODO: спуститься; вернуть node != null && node.isWord
        return false;
    }

    /** Есть ли хотя бы одно слово с таким префиксом. O(L). */
    public boolean startsWith(String prefix) {
        // TODO: спуститься; вернуть node != null
        return false;
    }

    /** Спуститься по строке и вернуть узел (или null). Удобный помощник. */
    private Node walk(String s) {
        Node cur = root;
        for (int i = 0; i < s.length() && cur != null; i++)
            cur = cur.children[s.charAt(i) - 'a'];
        return cur;
    }
}
