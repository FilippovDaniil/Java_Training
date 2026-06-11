package m13_iterator_visitor.practice.task06;

class Circle implements Shape {
    final int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void accept(ShapeVisitor v) {
        // TODO: v.visitCircle(this)
    }
}
