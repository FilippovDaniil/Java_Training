package m13_iterator_visitor.practice.task07;

class Withdrawal implements Transaction {
    final long amountCents;

    Withdrawal(long amountCents) {
        this.amountCents = amountCents;
    }

    @Override
    public void accept(TransactionVisitor v) {
        // TODO: v.visitWithdrawal(this)
    }
}
