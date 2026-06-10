/**
 * Модуль Data-Cruncher: индекс на хеш-таблице — поиск по ключу за O(1) в среднем.
 * Альтернатива BST/AVL-индексу (темы 12–13): нет порядка ключей, зато доступ
 * быстрее в среднем. Ключ — int (id записи), значение — строка (payload).
 */
public class HashIndex {

    static class Entry { int key; String value; Entry next; Entry(int k,String v){key=k;value=v;} }

    private Entry[] buckets = new Entry[16];
    private int size;
    static final double LOAD_FACTOR = 0.75;

    private int idx(int key, int cap) { return (Integer.hashCode(key) & 0x7fffffff) % cap; }

    /** Вставить/обновить запись. Амортизированно O(1). */
    public void put(int key, String value) {
        // TODO: вставка в цепочку (обновление при существующем ключе); resize при перегрузке
    }

    /** Найти payload по ключу или null. O(1) в среднем. */
    public String get(int key) {
        // TODO: поиск в цепочке нужной корзины
        return null;
    }

    private void resize() {
        // TODO: удвоить число корзин, перераспределить записи
    }

    public int size() { return size; }
}
