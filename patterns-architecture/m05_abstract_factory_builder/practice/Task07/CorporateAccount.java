package m05_abstract_factory_builder.practice.task07;

class CorporateAccount implements Account {
    @Override
    public String type() {
        // TODO: "corporate"
        return "";
    }

    @Override
    public long monthlyFeeCents() {
        // TODO: 1_000_00
        return 0;
    }
}
