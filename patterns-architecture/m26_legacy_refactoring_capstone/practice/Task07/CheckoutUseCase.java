package m26_legacy_refactoring_capstone.practice.task07;

// Входной порт: контракт сценария оформления (его реализуют и legacy, и clean).
interface CheckoutUseCase {
    String checkout(String orderId);
}
