package m17_hexagonal_ports_adapters.practice.task06;

// Выходной порт №1: хранилище.
interface AccountRepository {
    Account load(String id);
    void save(Account account);
}
