package m15_layered_mvc_clean.practice.task05;

// Use Case (внутренний слой): зависит только от порта AccountGateway.
class GetBalanceUseCase {
    // TODO: поле AccountGateway gateway + конструктор GetBalanceUseCase(AccountGateway gateway)

    public long balance(String id) {
        // TODO: gateway.load(id).balance()
        return 0;
    }
}
