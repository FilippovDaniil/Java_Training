package m02_arrays.practice.task02;

/** Стратегия удвоения: при заполнении ёмкость умножается на 2. */
public class GrowByDoubling {
    private int[] data = new int[1];
    private int size = 0;
    public long copies = 0;   // сколько элементов скопировано при расширениях

    public void add(int value) {
        // TODO: если size == data.length — создать массив вдвое больше,
        //       скопировать size элементов (copies += size), заменить data;
        //       затем data[size++] = value
    }
}
