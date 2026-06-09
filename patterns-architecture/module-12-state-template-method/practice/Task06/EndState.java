class EndState implements WorkflowState {
    @Override
    public WorkflowState advance() {
        // TODO: терминальное — остаётся this
        return this;
    }

    @Override
    public String label() {
        // TODO: "END"
        return "";
    }
}
