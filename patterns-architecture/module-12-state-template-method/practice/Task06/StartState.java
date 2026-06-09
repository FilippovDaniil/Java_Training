class StartState implements WorkflowState {
    @Override
    public WorkflowState advance() {
        // TODO: new EndState()
        return this;
    }

    @Override
    public String label() {
        // TODO: "START"
        return "";
    }
}
