package m14_hash_tables.practice.task01;

/**
 * Хеш-таблица с разрешением коллизий ЦЕПОЧКАМИ.
 * Каждая корзина — связный список записей (Entry). Ключи и значения — int
 * (для простоты; обобщение на generic — упражнение).
 */
public class MyHashMap {

    static class Entry {
        int key, value; Entry next;
        Entry(int key, int value) { this.key = key; this.value = value; }
    }

    private Entry[] buckets;
    private int size;

    public MyHashMap() { this(16); }
    public MyHashMap(int capacity) { this.buckets = new Entry[capacity]; }

    /** Индекс корзины для ключа. */
    private int indexFor(int key) {
        // TODO: (Integer.hashCode(key) & 0x7fffffff) % buckets.length
        return 0;
    }

    /** Положить пару (или обновить значение, если ключ уже есть). */
    public void put(int key, int value) {
        // TODO: найти корзину; пройти цепочку — если ключ есть, обновить; иначе добавить в начало; size++
    }

    /** Получить значение по ключу или null, если ключа нет. */
    public Integer get(int key) {
        // TODO: пройти цепочку нужной корзины
        return null;
    }

    public int size() { return size; }
}
