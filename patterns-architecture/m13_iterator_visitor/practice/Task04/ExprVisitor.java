package m13_iterator_visitor.practice.task04;

interface ExprVisitor {
    long visitNum(Num n);
    long visitAdd(Add a);
}
