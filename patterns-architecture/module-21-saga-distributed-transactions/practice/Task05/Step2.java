class Step2 implements SagaStep {
    @Override
    public boolean execute() {
        // TODO: напечатать "шаг 2 ok"; вернуть true
        return true;
    }

    @Override
    public void compensate() {
        // TODO: напечатать "откат 2"
    }
}
