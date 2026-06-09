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
