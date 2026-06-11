package m14_hash_tables.practice.task02;

/**
 * Хеш-таблица с цепочками. В этой задаче фокус на remove(key) и
 * корректном удалении звена из середины/начала/конца цепочки.
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

    private int indexFor(int key) { return (Integer.hashCode(key) & 0x7fffffff) % buckets.length; }

    public void put(int key, int value) {
        int i = indexFor(key);
        for (Entry e = buckets[i]; e != null; e = e.next)
            if (e.key == key) { e.value = value; return; }
        Entry head = new Entry(key, value);
        head.next = buckets[i];
        buckets[i] = head;
        size++;
    }

    public Integer get(int key) {
        for (Entry e = buckets[indexFor(key)]; e != null; e = e.next)
            if (e.key == key) return e.value;
        return null;
    }

    public boolean containsKey(int key) { return get(key) != null; }

    /** Удалить запись по ключу. Вернуть true, если что-то удалили. */
    public boolean remove(int key) {
        // TODO: найти корзину; пройти цепочку с указателем prev;
        //       перевязать prev.next (или buckets[i], если удаляем голову); size--
        return false;
    }

    public int size() { return size; }
}
