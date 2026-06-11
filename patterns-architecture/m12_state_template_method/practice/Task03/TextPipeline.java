package m12_state_template_method.practice.task03;

abstract class TextPipeline {
    // ШАБЛОННЫЙ МЕТОД: каркас алгоритма, не переопределяется.
    public final String run(String input) {
        // TODO: s = transform(input); если addExclaim() — добавить "!"; вернуть s
        return "";
    }

    protected abstract String transform(String input);

    // hook со значением по умолчанию
    protected boolean addExclaim() {
        return false;
    }
}
