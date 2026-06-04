interface PaymentMethod {
    boolean pay(double amount);
    String name();
}
