class Deposit implements Transaction {
    final long amountCents;

    Deposit(long amountCents) {
        this.amountCents = amountCents;
    }

    @Override
    public void accept(TransactionVisitor v) {
        // TODO: v.visitDeposit(this)
    }
}
