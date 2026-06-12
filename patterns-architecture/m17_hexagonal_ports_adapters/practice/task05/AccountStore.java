package m17_hexagonal_ports_adapters.practice.task05;

// Выходной (driven) порт.
interface AccountStore {
    Account load(String id);
    void save(Account account);
}
