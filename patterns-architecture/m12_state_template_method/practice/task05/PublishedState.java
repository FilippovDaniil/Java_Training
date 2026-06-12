package m12_state_template_method.practice.task05;

// Терминальное состояние: дальнейших переходов нет.
class PublishedState implements DocumentState {
    @Override
    public DocumentState submit() {
        return this;
    }

    @Override
    public DocumentState approve() {
        return this;
    }

    @Override
    public DocumentState reject() {
        return this;
    }

    @Override
    public String name() {
        // TODO: "PUBLISHED"
        return "";
    }
}
