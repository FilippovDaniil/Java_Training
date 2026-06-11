package m17_hexagonal_ports_adapters.practice.task07;

// Выходной (driven) порт.
interface AccountRepository {
    Account load(String id);
    void save(Account account);
}
