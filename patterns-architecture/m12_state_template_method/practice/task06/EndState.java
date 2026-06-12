package m12_state_template_method.practice.task06;

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
