package m07_adapter_decorator.practice.task05;

abstract class ChannelDecorator implements DataChannel {
    protected final DataChannel inner;

    protected ChannelDecorator(DataChannel inner) {
        this.inner = inner;
    }
}
