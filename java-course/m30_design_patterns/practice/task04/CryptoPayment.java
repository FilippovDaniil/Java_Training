package m30_design_patterns.practice.task04;

public class CryptoPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Оплата криптовалютой на сумму: " + amount);
    }
}
