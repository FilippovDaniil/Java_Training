package m21_saga_distributed_transactions.practice.task02;

interface SagaStep {
    void execute();
    void compensate();
}
