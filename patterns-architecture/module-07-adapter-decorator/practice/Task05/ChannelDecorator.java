abstract class ChannelDecorator implements DataChannel {
    protected final DataChannel inner;

    protected ChannelDecorator(DataChannel inner) {
        this.inner = inner;
    }
}
