interface SagaStep {
    boolean execute();   // true — успех, false — неудача
    void compensate();
}
