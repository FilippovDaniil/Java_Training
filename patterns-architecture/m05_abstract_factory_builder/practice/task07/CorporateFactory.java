package m05_abstract_factory_builder.practice.task07;

class CorporateFactory implements BankProductFactory {
    @Override
    public Account createAccount() {
        // TODO: new CorporateAccount()
        return null;
    }

    @Override
    public Card createCard() {
        // TODO: new CorporateCard()
        return null;
    }
}
