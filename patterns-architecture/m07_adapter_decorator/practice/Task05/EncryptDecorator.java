package m07_adapter_decorator.practice.task05;

class EncryptDecorator extends ChannelDecorator {
    public EncryptDecorator(DataChannel inner) {
        super(inner);
    }

    @Override
    public String pack(String data) {
        // TODO: "E(" + inner.pack(data) + ")"
        return "";
    }
}
