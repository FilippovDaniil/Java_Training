interface Expr {
    long accept(ExprVisitor v);
}
