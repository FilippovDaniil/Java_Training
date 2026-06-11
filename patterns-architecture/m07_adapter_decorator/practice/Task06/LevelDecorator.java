package m07_adapter_decorator.practice.task06;

class LevelDecorator extends LoggerDecorator {
    public LevelDecorator(Logger inner) {
        super(inner);
    }

    @Override
    public void log(String msg) {
        // TODO: inner.log("[INFO] " + msg)
    }
}
