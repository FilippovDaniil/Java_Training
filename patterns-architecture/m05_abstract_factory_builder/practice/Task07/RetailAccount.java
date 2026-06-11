package m05_abstract_factory_builder.practice.task07;

class RetailAccount implements Account {
    @Override
    public String type() {
        // TODO: "retail"
        return "";
    }

    @Override
    public long monthlyFeeCents() {
        // TODO: 0
        return 0;
    }
}
