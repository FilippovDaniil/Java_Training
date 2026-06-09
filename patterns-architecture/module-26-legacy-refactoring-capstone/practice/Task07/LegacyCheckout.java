// «ДО» — god-сервис: цена и оплата свалены в один метод, без границ и абстракций.
// Используется как «старая» ветка в MigrationRouter (пока не отмигрировали).
class LegacyCheckout implements CheckoutUseCase {
    @Override
    public String checkout(String orderId) {
        // имитация: всё инлайн, без портов
        long price = orderId.length() * 1000L;   // «расчёт»
        boolean paid = price > 0;                 // «оплата»
        return paid ? "[legacy] оформлен " + orderId : "[legacy] отклонён " + orderId;
    }
}
