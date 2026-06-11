package m19_ddd_tactical.practice.task03;

interface AccountRepository {
    Account findById(String id);
    void save(Account account);
}
