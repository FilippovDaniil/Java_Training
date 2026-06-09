class NameValidator extends Validator {
    @Override
    public String validate(Request r) {
        // TODO: если имя пустое → "имя пустое"; иначе passToNext(r)
        return "";
    }
}
