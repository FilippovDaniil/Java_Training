// CoR: конвейер валидации транзакции.
abstract class Validator {
    protected Validator next;

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public abstract String validate(long amount);

    protected String passToNext(long amount) {
        // TODO: next != null → next.validate(amount); иначе "ok"
        return "ok";
    }
}
