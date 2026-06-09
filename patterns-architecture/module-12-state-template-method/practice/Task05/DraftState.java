class DraftState implements DocumentState {
    @Override
    public DocumentState submit() {
        // TODO: перейти в new ModerationState()
        return this;
    }

    @Override
    public DocumentState approve() {
        // TODO: из черновика нельзя одобрить — остаётся this
        return this;
    }

    @Override
    public DocumentState reject() {
        // TODO: остаётся this
        return this;
    }

    @Override
    public String name() {
        // TODO: "DRAFT"
        return "";
    }
}
