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
