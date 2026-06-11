package m11_observer_chain_of_responsibility.practice.task05;

class NameValidator extends Validator {
    @Override
    public String validate(Request r) {
        // TODO: если имя пустое → "имя пустое"; иначе passToNext(r)
        return "";
    }
}
