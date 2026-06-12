package m26_legacy_refactoring_capstone.practice.task07;

// Выходной порт (BAM): проведение оплаты.
interface PaymentPort {
    boolean charge(long amount);
}
