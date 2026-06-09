class AgeValidator extends Validator {
    @Override
    public String validate(Request r) {
        // TODO: если age < 18 → "возраст < 18"; иначе passToNext(r)
        return "";
    }
}
