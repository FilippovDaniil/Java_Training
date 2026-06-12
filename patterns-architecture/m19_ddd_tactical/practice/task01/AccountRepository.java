package m19_ddd_tactical.practice.task01;

// Контракт в терминах домена (это же выходной порт гексагона, тема 17).
interface AccountRepository {
    Account findById(String id);
    void save(Account account);
}
