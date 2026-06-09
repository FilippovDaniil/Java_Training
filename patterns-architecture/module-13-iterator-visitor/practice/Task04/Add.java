class Add implements Expr {
    final Expr left;
    final Expr right;

    Add(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public long accept(ExprVisitor v) {
        // TODO: v.visitAdd(this)
        return 0;
    }
}
