// Выходной (driven) порт.
interface AccountRepository {
    Account load(String id);
    void save(Account account);
}
