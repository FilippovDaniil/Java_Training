class LimitValidator extends Validator {
    @Override
    public String validate(long amount) {
        // TODO: Math.abs(amount) > 100000 → "превышен лимит"; иначе passToNext(amount)
        return "";
    }
}
