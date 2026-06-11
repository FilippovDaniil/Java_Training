package m11_observer_chain_of_responsibility.practice.task07;

class NonZeroValidator extends Validator {
    @Override
    public String validate(long amount) {
        // TODO: amount == 0 → "нулевая сумма"; иначе passToNext(amount)
        return "";
    }
}
