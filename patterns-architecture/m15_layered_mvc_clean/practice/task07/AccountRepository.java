package m15_layered_mvc_clean.practice.task07;

interface AccountRepository {
    Account findById(String id);
    void save(Account account);
}
