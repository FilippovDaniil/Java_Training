package m12_state_template_method.practice.task05;

class ModerationState implements DocumentState {
    @Override
    public DocumentState submit() {
        // TODO: уже на модерации — остаётся this
        return this;
    }

    @Override
    public DocumentState approve() {
        // TODO: перейти в new PublishedState()
        return this;
    }

    @Override
    public DocumentState reject() {
        // TODO: перейти в new RejectedState()
        return this;
    }

    @Override
    public String name() {
        // TODO: "MODERATION"
        return "";
    }
}
