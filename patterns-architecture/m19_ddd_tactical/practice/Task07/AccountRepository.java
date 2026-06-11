package m19_ddd_tactical.practice.task07;

interface AccountRepository {
    Account findById(String id);
    void save(Account account);
}
