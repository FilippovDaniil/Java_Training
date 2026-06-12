package m13_iterator_visitor.practice.task04;

// Вторая операция «посчитать числа» — добавлена БЕЗ изменения Num/Add.
class CountNumVisitor implements ExprVisitor {
    @Override
    public long visitNum(Num n) {
        // TODO: одно число = 1
        return 0;
    }

    @Override
    public long visitAdd(Add a) {
        // TODO: a.left.accept(this) + a.right.accept(this)
        return 0;
    }
}
