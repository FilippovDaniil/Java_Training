package m01_oop_principles.practice.task04;

class CardPayment implements PaymentMethod {

    // TODO: поле balanceCents + конструктор CardPayment(long balanceCents)

    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public boolean pay(long amountCents) {
        // TODO: списать, если хватает средств; иначе вернуть false
        return false;
    }
}
