// Presentation: тонкий контроллер, делегирует сервису.
class AccountController {
    // TODO: поле AccountService service + конструктор AccountController(AccountService service)

    public void open(String id) {
        // TODO: делегировать
    }

    public void deposit(String id, long amountCents) {
        // TODO: делегировать
    }

    public void withdraw(String id, long amountCents) {
        // TODO: делегировать
    }

    public AccountDto view(String id) {
        // TODO: делегировать service.view(id)
        return null;
    }
}
