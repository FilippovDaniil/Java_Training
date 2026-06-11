package m13_iterator_visitor.practice.task04;

interface Expr {
    long accept(ExprVisitor v);
}
