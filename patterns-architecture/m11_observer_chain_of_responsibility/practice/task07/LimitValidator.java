package m11_observer_chain_of_responsibility.practice.task07;

class LimitValidator extends Validator {
    @Override
    public String validate(long amount) {
        // TODO: Math.abs(amount) > 100000 → "превышен лимит"; иначе passToNext(amount)
        return "";
    }
}
