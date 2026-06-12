package m08_proxy_facade.practice.task07;

class PaymentService {
    public boolean charge(long amountCents) {
        // TODO: для простоты — успешно при amountCents > 0
        return amountCents > 0;
    }
}
