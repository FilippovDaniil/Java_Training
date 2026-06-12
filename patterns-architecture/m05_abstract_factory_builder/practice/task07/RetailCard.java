package m05_abstract_factory_builder.practice.task07;

class RetailCard implements Card {
    @Override
    public String type() {
        // TODO: "retail"
        return "";
    }

    @Override
    public long limitCents() {
        // TODO: 5_000_00
        return 0;
    }
}
