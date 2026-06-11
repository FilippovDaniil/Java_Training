package m07_adapter_decorator.practice.task01;

// Адаптер: реализует PaymentGateway, внутри делегирует LegacyPayment.
class LegacyPaymentAdapter implements PaymentGateway {
    // TODO: поле LegacyPayment legacy + конструктор LegacyPaymentAdapter(LegacyPayment legacy)

    @Override
    public boolean pay(long amountCents) {
        // TODO: вызвать legacy.doPayment((int) amountCents), вернуть true
        return false;
    }
}
