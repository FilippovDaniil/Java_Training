interface SagaStep {
    void execute();
    void compensate();
}
