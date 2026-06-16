package m22_oop_encapsulation_polymorphism_interfaces.practice.task07;

public class Cash implements PaymentMethod{
    private double cash;

    public Cash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean pay(double amount) {
        if (amount < 0){
            System.out.println("Оплата отрицательной суммой невозможна");
            return false;
        } else if (amount == 0){
            System.out.println("Оплата суммой равной нулю ничего не изменит");
            return false;
        } else {
            if ((cash - amount) < 0){
                System.out.println("Недостаточно средств. Попробуйте ввести сумму, не больше чем " + cash);
                return false;
            } else {
                cash = cash - amount;
                System.out.println("Оплата успешна. Осталось: " + (cash) + " средств");
                return true;
            }
        }
    }

    @Override
    public String name() {
        return "Cash";
    }
}
