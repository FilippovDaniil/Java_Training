package m26_legacy_refactoring_capstone.practice.task07;

// Чистое ядро: реализует входной порт, зависит только от выходных портов (гексагон).
class CleanCheckout implements CheckoutUseCase {
    // TODO: поля final OrderPricing pricing, final PaymentPort payment + конструктор

    @Override
    public String checkout(String orderId) {
        // TODO: long price = pricing.priceOf(orderId);
        //       if (payment.charge(price)) return "[clean] оформлен " + orderId + " на " + price;
        //       else return "[clean] отклонён " + orderId;
        return "";
    }
}
