package m13_iterator_visitor.practice.task03;

// Операция «площадь» вынесена из классов фигур в визитор.
class AreaVisitor implements ShapeVisitor {
    private long total = 0;

    @Override
    public void visitCircle(Circle c) {
        // TODO: total += (long)(Math.PI * c.radius * c.radius)
    }

    @Override
    public void visitSquare(Square s) {
        // TODO: total += (long) s.side * s.side
    }

    public long total() {
        // TODO: вернуть накопленную площадь
        return total;
    }
}
