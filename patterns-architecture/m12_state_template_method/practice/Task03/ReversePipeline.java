package m12_state_template_method.practice.task03;

class ReversePipeline extends TextPipeline {
    @Override
    protected String transform(String input) {
        // TODO: new StringBuilder(input).reverse().toString()
        return "";
    }

    @Override
    protected boolean addExclaim() {
        // TODO: true — этот конвейер добавляет "!"
        return true;
    }
}
