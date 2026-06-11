package m05_abstract_factory_builder.practice.task07;

class RetailFactory implements BankProductFactory {
    @Override
    public Account createAccount() {
        // TODO: new RetailAccount()
        return null;
    }

    @Override
    public Card createCard() {
        // TODO: new RetailCard()
        return null;
    }
}
