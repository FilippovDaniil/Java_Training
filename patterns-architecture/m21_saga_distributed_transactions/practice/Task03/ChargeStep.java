package m21_saga_distributed_transactions.practice.task03;

class ChargeStep implements SagaStep {
    @Override
    public void execute() {
        // TODO: напечатать "оплачено"
    }

    @Override
    public void compensate() {
        // TODO: напечатать "оплата возвращена"
    }
}
