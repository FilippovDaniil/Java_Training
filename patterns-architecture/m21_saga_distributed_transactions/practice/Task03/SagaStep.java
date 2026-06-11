package m21_saga_distributed_transactions.practice.task03;

interface SagaStep {
    void execute();
    void compensate();
}
