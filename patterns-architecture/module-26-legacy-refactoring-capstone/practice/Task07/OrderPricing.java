// Выходной порт (OPS): расчёт цены заказа.
interface OrderPricing {
    long priceOf(String orderId);
}
