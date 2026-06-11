package m17_hexagonal_ports_adapters.practice.task06;

// Ядро: зависит от двух выходных портов (репозиторий + аудит).
class WithdrawService {
    // TODO: поля final AccountRepository repo и final AuditPort audit + конструктор

    public void withdraw(String id, long amount) {
        // TODO: load(id) → account.withdraw(amount) → save(account)
        //       → audit.record("списано " + amount + " со счёта " + id)
    }
}
