package m13_iterator_visitor.practice.task06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Iterable-коллекция фигур (Iterator) — по ней работает for-each.
class ShapeBag implements Iterable<Shape> {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape) {
        // TODO: добавить в shapes
    }

    @Override
    public Iterator<Shape> iterator() {
        // TODO: вернуть итератор по shapes (можно shapes.iterator())
        return null;
    }
}
