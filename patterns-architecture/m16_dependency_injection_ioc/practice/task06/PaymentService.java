package m16_dependency_injection_ioc.practice.task06;

class PaymentService {
    // TODO: поле final PaymentGateway gateway + конструктор PaymentService(PaymentGateway gateway)

    public boolean pay(long amount) {
        // TODO: делегировать gateway.charge(amount)
        return false;
    }
}
