package m30_design_patterns.practice.task04;

public class CashPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Оплата наличными на сумму: " + amount);
    }
}
