package m21_saga_distributed_transactions.practice.task05;

class Step1 implements SagaStep {
    @Override
    public boolean execute() {
        // TODO: напечатать "шаг 1 ok"; вернуть true
        return true;
    }

    @Override
    public void compensate() {
        // TODO: напечатать "откат 1"
    }
}
