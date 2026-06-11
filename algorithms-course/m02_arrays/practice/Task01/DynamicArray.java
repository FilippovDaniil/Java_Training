package m02_arrays.practice.task01;

/**
 * Свой динамический массив целых чисел (упрощённый ArrayList).
 * Хранит элементы в обычном int[] и растёт при заполнении.
 */
public class DynamicArray {

    private int[] data;   // внутренний массив (ёмкость = data.length)
    private int size;     // число реально занятых элементов

    public DynamicArray() {
        // TODO: начальная ёмкость (например 4), size = 0
        this.data = new int[4];
        this.size = 0;
    }

    /** Добавить элемент в конец. Амортизированно O(1). */
    public void add(int value) {
        // TODO: если size == data.length — расширить (см. ensureCapacity);
        //       data[size++] = value;
    }

    /** Получить элемент по индексу. O(1). */
    public int get(int index) {
        // TODO: проверить границы (0..size-1), вернуть data[index]
        return 0;
    }

    /** Удалить элемент по индексу со сдвигом хвоста. O(n). */
    public void remove(int index) {
        // TODO: проверить границы; сдвинуть хвост влево (System.arraycopy); size--
    }

    /** Текущее число элементов. */
    public int size() {
        return size;
    }

    /** Расширить внутренний массив (удвоить ёмкость). */
    private void ensureCapacity() {
        // TODO: создать новый массив вдвое больше, скопировать data в него
    }
}
