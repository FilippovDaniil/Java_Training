interface PaymentMethod {
    String name();
    boolean pay(long amountCents);
}
