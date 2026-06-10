/**
 * Модуль Data-Cruncher: LRU-кэш последних операций.
 * Идея: HashMap (ключ -> узел) для доступа за O(1) + двусвязный список порядка
 * использования. Самый свежий — у головы, самый старый (кандидат на вытеснение) — у хвоста.
 *
 * Здесь — задел: реализуйте get/put за O(1). В теме 14 вернёмся к этому на хеш-таблицах.
 */
import java.util.HashMap;
import java.util.Map;

public class LruCache {

    static class Node {
        int key, value; Node prev, next;
        Node(int key, int value) { this.key = key; this.value = value; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;   // head = самый свежий, tail = самый старый

    public LruCache(int capacity) { this.capacity = capacity; }

    /** Вернуть значение по ключу или -1, если нет. Делает элемент «свежим». O(1). */
    public int get(int key) {
        // TODO: если есть в map — переместить узел в голову, вернуть value; иначе -1
        return -1;
    }

    /** Положить пару. При переполнении вытеснить самый старый (хвост). O(1). */
    public void put(int key, int value) {
        // TODO: если ключ есть — обновить value и поднять в голову;
        //       иначе создать узел, добавить в голову и map;
        //       если size > capacity — удалить хвостовой узел из списка и map
    }

    // вспомогательные методы перевязки списка — реализуйте по необходимости
    // private void moveToHead(Node n) { ... }
    // private void removeNode(Node n) { ... }
    // private void addToHead(Node n) { ... }
}
