package m07_adapter_decorator.practice.task05;

class CompressDecorator extends ChannelDecorator {
    public CompressDecorator(DataChannel inner) {
        super(inner);
    }

    @Override
    public String pack(String data) {
        // TODO: "C(" + inner.pack(data) + ")"
        return "";
    }
}
