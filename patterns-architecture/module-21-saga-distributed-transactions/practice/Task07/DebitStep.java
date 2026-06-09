class DebitStep implements SagaStep {
    // TODO: поля Account account, long amount + конструктор DebitStep(Account account, long amount)

    @Override
    public void execute() {
        // TODO: account.debit(amount); напечатать "списано " + amount + " со счёта " + account.id()
    }

    @Override
    public void compensate() {
        // TODO: account.credit(amount); напечатать "возврат " + amount + " на счёт " + account.id()
    }
}
