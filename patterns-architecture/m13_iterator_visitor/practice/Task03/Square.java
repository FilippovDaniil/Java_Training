package m13_iterator_visitor.practice.task03;

class Square implements Shape {
    final int side;

    Square(int side) {
        this.side = side;
    }

    @Override
    public void accept(ShapeVisitor v) {
        // TODO: v.visitSquare(this)
    }
}
