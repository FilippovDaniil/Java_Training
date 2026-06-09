// Выходной порт (BAM): проведение оплаты.
interface PaymentPort {
    boolean charge(long amount);
}
