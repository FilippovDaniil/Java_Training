abstract class ProcessorDecorator implements TextProcessor {
    protected final TextProcessor inner;

    protected ProcessorDecorator(TextProcessor inner) {
        this.inner = inner;
    }
}
