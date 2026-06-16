package m22_oop_encapsulation_polymorphism_interfaces.practice.task07;

public class DIgitalWallet implements PaymentMethod{
    private double balance;
    private double discount;

    public DIgitalWallet(double balance, double discount) {
        this.balance = balance;
        this.discount = discount;
    }

    @Override
    public String name() {
        return "DigitalWallet";
    }

    @Override
    public boolean pay(double amount) {
        amount = amount * (100 - discount) / 100;
        if (amount < 0){
            System.out.println("Оплата отрицательной суммой невозможна");
            return false;
        } else if (amount == 0){
            System.out.println("Оплата суммой равной нулю ничего не изменит");
            return false;
        } else {
            if ((balance - amount) < 0){
                System.out.println("Недостаточно средств. Попробуйте ввести сумму, не больше чем " + balance);
                return false;
            } else {
                balance = balance - amount;
                System.out.println("Оплата успешна. Осталось: " + (balance) + " средств");
                return true;
            }
        }
    }
}
