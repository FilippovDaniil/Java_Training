package m07_adapter_decorator.practice.task04;

class Identity implements TextProcessor {
    @Override
    public String process(String input) {
        // TODO: вернуть вход без изменений
        return input;
    }
}
