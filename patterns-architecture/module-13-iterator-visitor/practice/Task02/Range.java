import java.util.Iterator;

// Диапазон чисел [from, to]; элементы вычисляются итератором, не хранятся.
class Range implements Iterable<Integer> {
    private final int from;
    private final int to;

    Range(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Iterator<Integer> iterator() {
        // TODO: вернуть Iterator с полем current = from;
        //   hasNext() = current <= to; next() = вернуть current, затем current++
        return null;
    }
}
