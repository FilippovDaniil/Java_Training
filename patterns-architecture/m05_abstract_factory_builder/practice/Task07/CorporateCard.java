package m05_abstract_factory_builder.practice.task07;

class CorporateCard implements Card {
    @Override
    public String type() {
        // TODO: "corporate"
        return "";
    }

    @Override
    public long limitCents() {
        // TODO: 100_000_00
        return 0;
    }
}
