// Входной порт: контракт сценария оформления (его реализуют и legacy, и clean).
interface CheckoutUseCase {
    String checkout(String orderId);
}
