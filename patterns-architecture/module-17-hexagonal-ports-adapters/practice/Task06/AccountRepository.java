// Выходной порт №1: хранилище.
interface AccountRepository {
    Account load(String id);
    void save(Account account);
}
