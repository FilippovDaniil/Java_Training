class RealPaymentGateway implements PaymentGateway {
    @Override
    public boolean charge(long amount) {
        // TODO: «боевая» оплата — успех при amount > 0
        return amount > 0;
    }
}
