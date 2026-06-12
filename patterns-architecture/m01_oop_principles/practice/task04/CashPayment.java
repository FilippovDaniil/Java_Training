package m01_oop_principles.practice.task04;

class CashPayment implements PaymentMethod {

    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public boolean pay(long amountCents) {
        // TODO: наличные всегда проходят; напечатайте чек и верните true
        return false;
    }
}
