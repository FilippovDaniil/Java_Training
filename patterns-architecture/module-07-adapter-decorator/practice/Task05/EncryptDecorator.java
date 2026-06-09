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
