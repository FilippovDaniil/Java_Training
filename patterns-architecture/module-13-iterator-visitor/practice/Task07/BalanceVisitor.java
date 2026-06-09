// Отчёт «чистый баланс» — операция вынесена в визитор (double dispatch).
class BalanceVisitor implements TransactionVisitor {
    private long net = 0;

    @Override
    public void visitDeposit(Deposit d) {
        // TODO: net += d.amountCents
    }

    @Override
    public void visitWithdrawal(Withdrawal w) {
        // TODO: net -= w.amountCents
    }

    public long net() {
        return net;
    }
}
