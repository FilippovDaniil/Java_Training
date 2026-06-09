interface ExprVisitor {
    long visitNum(Num n);
    long visitAdd(Add a);
}
