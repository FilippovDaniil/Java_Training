import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Bag<T> implements Iterable<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        // TODO: добавить в items
    }

    @Override
    public Iterator<T> iterator() {
        // TODO: вернуть свой Iterator (анонимный класс):
        //   поле int i = 0; hasNext() = i < items.size(); next() = items.get(i++)
        return null;
    }
}
