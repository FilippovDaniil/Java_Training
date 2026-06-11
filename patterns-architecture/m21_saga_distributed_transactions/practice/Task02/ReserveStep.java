package m21_saga_distributed_transactions.practice.task02;

class ReserveStep implements SagaStep {
    @Override
    public void execute() {
        // TODO: напечатать "зарезервировано"
    }

    @Override
    public void compensate() {
        // TODO: напечатать "резерв снят"
    }
}
