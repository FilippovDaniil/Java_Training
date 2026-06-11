package m21_saga_distributed_transactions.practice.task07;

class CreditStep implements SagaStep {
    // TODO: поля Account account, long amount, boolean willFail + конструктор
    //       CreditStep(Account account, long amount, boolean willFail)

    @Override
    public void execute() {
        // TODO: если willFail → напечатать "зачисление на " + account.id() + " не удалось"
        //       и бросить new RuntimeException("credit failed");
        //       иначе account.credit(amount) и печать "зачислено " + amount + " на " + account.id()
    }

    @Override
    public void compensate() {
        // TODO: account.debit(amount)
    }
}
