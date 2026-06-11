package m07_adapter_decorator.practice.task04;

class UpperCaseDecorator extends ProcessorDecorator {
    public UpperCaseDecorator(TextProcessor inner) {
        super(inner);
    }

    @Override
    public String process(String input) {
        // TODO: inner.process(input), затем .toUpperCase()
        return "";
    }
}
