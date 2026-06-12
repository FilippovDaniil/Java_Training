package m07_adapter_decorator.practice.task04;

class TrimDecorator extends ProcessorDecorator {
    public TrimDecorator(TextProcessor inner) {
        super(inner);
    }

    @Override
    public String process(String input) {
        // TODO: inner.process(input), затем .trim()
        return "";
    }
}
