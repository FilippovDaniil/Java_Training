// Business: сценарии и инварианты; зависит от интерфейса репозитория.
class AccountService {
    // TODO: поле AccountRepository repo + конструктор AccountService(AccountRepository repo)

    public void open(String id) {
        // TODO: создать new Account(id) и сохранить
    }

    public void deposit(String id, long amountCents) {
        // TODO: найти счёт, deposit(amount), save
    }

    public void withdraw(String id, long amountCents) {
        // TODO: найти счёт, withdraw(amount) (может кинуть IllegalStateException), save
    }

    public AccountDto view(String id) {
        // TODO: найти счёт и вернуть new AccountDto(id, balanceCents) — наружу DTO
        return null;
    }
}
