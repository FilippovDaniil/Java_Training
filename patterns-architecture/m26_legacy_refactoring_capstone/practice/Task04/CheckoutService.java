package m26_legacy_refactoring_capstone.practice.task04;

// Клиент зависит от абстракции; реализацию подменяют под ним.
class CheckoutService {
    // TODO: поле final PaymentProcessor processor + конструктор CheckoutService(PaymentProcessor processor)

    public String checkout(long amount) {
        // TODO: processor.pay(amount) ? "оплачено" : "отклонено" (вывести результат)
        return "";
    }
}
