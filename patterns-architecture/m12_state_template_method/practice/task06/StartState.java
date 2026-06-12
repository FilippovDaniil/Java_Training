package m12_state_template_method.practice.task06;

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
