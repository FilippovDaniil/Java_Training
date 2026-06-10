/**
 * Двоичная min-heap на массиве: минимум всегда в корне (индекс 0).
 * Дети узла i: 2i+1 и 2i+2; родитель: (i-1)/2.
 */
public class MinHeap {

    private int[] a;
    private int size;

    public MinHeap() { this(16); }
    public MinHeap(int capacity) { this.a = new int[Math.max(1, capacity)]; }

    /** Вставить элемент: в конец + siftUp. O(log n). */
    public void insert(int value) {
        // TODO: при заполнении — расширить массив; a[size]=value; siftUp(size); size++
    }

    /** Извлечь минимум: a[0] <-> a[size-1], size--, siftDown(0). O(log n). */
    public int extractMin() {
        // TODO: проверить пустоту; запомнить a[0]; перенести последний в корень; siftDown(0)
        return 0;
    }

    /** Посмотреть минимум без извлечения. O(1). */
    public int peek() {
        // TODO
        return 0;
    }

    private void siftUp(int i) {
        // TODO: пока i>0 и родитель больше — менять с родителем
    }

    private void siftDown(int i) {
        // TODO: пока есть дети — менять с МЕНЬШИМ ребёнком, если он меньше
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
}
