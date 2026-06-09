// Strangler + Feature Toggle поверх общего порта: переключает legacy → clean.
class MigrationRouter implements CheckoutUseCase {
    // TODO: поля final CheckoutUseCase legacy, final CheckoutUseCase clean, boolean useClean + конструктор

    public void setUseClean(boolean useClean) {
        // TODO: переключить флаг
    }

    @Override
    public String checkout(String orderId) {
        // TODO: useClean ? clean.checkout(orderId) : legacy.checkout(orderId)
        return "";
    }
}
