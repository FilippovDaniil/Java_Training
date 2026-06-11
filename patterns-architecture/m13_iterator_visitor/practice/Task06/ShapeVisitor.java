package m13_iterator_visitor.practice.task06;

interface ShapeVisitor {
    void visitCircle(Circle c);
    void visitSquare(Square s);
}
