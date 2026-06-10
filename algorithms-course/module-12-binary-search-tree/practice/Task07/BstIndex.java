/**
 * Модуль Data-Cruncher: индекс данных на основе BST для быстрого поиска по ключу.
 * Хранит пары (ключ -> значение/строка) в BST по ключу. Поиск/вставка O(h).
 * (В теме 13 заменим на AVL ради гарантированного O(log n).)
 */
public class BstIndex {

    static class Node {
        int key; String payload; Node left, right;
        Node(int key, String payload) { this.key = key; this.payload = payload; }
    }

    private Node root;

    /** Вставить/обновить запись по ключу. O(h). */
    public void put(int key, String payload) {
        // TODO: спуск по BST; при совпадении ключа — обновить payload
    }

    /** Найти payload по ключу или null. O(h). */
    public String get(int key) {
        // TODO: спуск по дереву
        return null;
    }

    /** Все payload'ы в порядке возрастания ключа (in-order). */
    public java.util.List<String> rangeAll() {
        // TODO: in-order обход, собрать payload'ы
        return new java.util.ArrayList<>();
    }
}
