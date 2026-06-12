package m21_saga_distributed_transactions.practice.task05;

// Этот шаг неуспешен — он запускает откат предыдущих.
class Step3 implements SagaStep {
    @Override
    public boolean execute() {
        // TODO: напечатать "шаг 3 FAIL"; вернуть false
        return false;
    }

    @Override
    public void compensate() {
        // TODO: (не вызовется) напечатать "откат 3"
    }
}
