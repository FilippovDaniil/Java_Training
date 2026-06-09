// Abstract Factory: согласованная семья банковских продуктов одного тарифа.
interface BankProductFactory {
    Account createAccount();
    Card createCard();
}
