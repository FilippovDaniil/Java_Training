package m26_legacy_refactoring_capstone.practice.task07;

// Выходной порт (OPS): расчёт цены заказа.
interface OrderPricing {
    long priceOf(String orderId);
}
