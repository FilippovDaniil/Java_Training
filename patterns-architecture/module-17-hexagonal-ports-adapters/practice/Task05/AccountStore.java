// Выходной (driven) порт.
interface AccountStore {
    Account load(String id);
    void save(Account account);
}
