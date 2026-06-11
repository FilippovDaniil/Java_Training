package m01_oop_principles.practice.task04;

interface PaymentMethod {
    String name();
    boolean pay(long amountCents);
}
