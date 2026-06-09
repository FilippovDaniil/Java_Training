// Тестовый двойник: не платит реально, но фиксирует вызовы для проверки.
class FakePaymentGateway implements PaymentGateway {
    private int chargeCount = 0;

    @Override
    public boolean charge(long amount) {
        // TODO: увеличить chargeCount и вернуть true (без реальной оплаты)
        return true;
    }

    public int chargeCount() {
        return chargeCount;
    }
}
