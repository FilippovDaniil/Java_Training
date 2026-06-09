// Операция «вычислить значение» — без правок Num/Add.
class EvalVisitor implements ExprVisitor {
    @Override
    public long visitNum(Num n) {
        // TODO: вернуть n.value
        return 0;
    }

    @Override
    public long visitAdd(Add a) {
        // TODO: a.left.accept(this) + a.right.accept(this)
        return 0;
    }
}
