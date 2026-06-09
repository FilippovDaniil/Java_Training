// Template Method: каркас одного шага; обрамление задают подклассы.
abstract class WorkflowRunner {
    public final String runStep(Workflow w) {
        // TODO: вернуть before() + "[" + (w.advance(); затем w.label()) + "]" + after()
        return "";
    }

    protected abstract String before();
    protected abstract String after();
}
