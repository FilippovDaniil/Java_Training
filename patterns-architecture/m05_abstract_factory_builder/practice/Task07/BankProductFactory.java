package m05_abstract_factory_builder.practice.task07;

// Abstract Factory: согласованная семья банковских продуктов одного тарифа.
interface BankProductFactory {
    Account createAccount();
    Card createCard();
}
