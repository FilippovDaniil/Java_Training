package m21_saga_distributed_transactions.practice.task05;

interface SagaStep {
    boolean execute();   // true — успех, false — неудача
    void compensate();
}
