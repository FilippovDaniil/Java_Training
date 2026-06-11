package m26_legacy_refactoring_capstone.practice.task07;

// Driven-адаптер порта PaymentPort (заглушка: всегда успешно).
class AlwaysOkPayment implements PaymentPort {
    @Override
    public boolean charge(long amount) {
        // TODO: вернуть true (для примера оплата всегда проходит)
        return false;
    }
}
