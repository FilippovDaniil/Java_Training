package m12_state_template_method.practice.task05;

class Document {
    private DocumentState state = new DraftState();

    public void submit() {
        // TODO: state = state.submit()
    }

    public void approve() {
        // TODO: state = state.approve()
    }

    public void reject() {
        // TODO: state = state.reject()
    }

    public String status() {
        // TODO: state.name()
        return "";
    }
}
