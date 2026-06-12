package m13_iterator_visitor.practice.task04;

class Num implements Expr {
    final long value;

    Num(long value) {
        this.value = value;
    }

    @Override
    public long accept(ExprVisitor v) {
        // TODO: v.visitNum(this)
        return 0;
    }
}
