package m21_saga_distributed_transactions.practice.task07;

interface SagaStep {
    void execute();
    void compensate();
}
