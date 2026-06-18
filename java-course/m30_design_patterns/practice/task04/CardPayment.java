package m30_design_patterns.practice.task04;

public class CardPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Оплата картой на сумму: " + amount);
    }
}
