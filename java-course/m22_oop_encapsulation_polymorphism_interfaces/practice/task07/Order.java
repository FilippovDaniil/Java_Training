package m22_oop_encapsulation_polymorphism_interfaces.practice.task07;

public class Order {
    private double price;

    public Order(double price) {
        this.price = price;
    }

    public String checkOut(PaymentMethod paymentMethod){
        return paymentMethod.name() + ": " + paymentMethod.pay(price);
    }
}
