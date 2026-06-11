package m07_adapter_decorator.practice.task04;

abstract class ProcessorDecorator implements TextProcessor {
    protected final TextProcessor inner;

    protected ProcessorDecorator(TextProcessor inner) {
        this.inner = inner;
    }
}
