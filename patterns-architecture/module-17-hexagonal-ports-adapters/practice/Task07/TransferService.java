// Ядро: реализует входной порт, использует выходной. Адаптеров не знает.
class TransferService implements TransferUseCase {
    // TODO: поле final AccountRepository accounts + конструктор TransferService(AccountRepository accounts)

    @Override
    public void transfer(String fromId, String toId, long amount) {
        // TODO: load(fromId), load(toId); from.withdraw(amount) (кинет при нехватке);
        //       to.deposit(amount); save(from); save(to)
    }
}
