package m02_arrays.practice.task02;

/** Стратегия «грязного» роста: ёмкость увеличивается на +1 при каждом заполнении. */
public class GrowByOne {
    private int[] data = new int[1];
    private int size = 0;
    public long copies = 0;   // сколько элементов скопировано при расширениях

    public void add(int value) {
        // TODO: если size == data.length — создать массив на +1 больше,
        //       скопировать size элементов (copies += size), заменить data;
        //       затем data[size++] = value
    }
}
