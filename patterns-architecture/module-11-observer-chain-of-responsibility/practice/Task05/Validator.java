abstract class Validator {
    protected Validator next;

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public abstract String validate(Request r);

    protected String passToNext(Request r) {
        // TODO: next != null → next.validate(r); иначе "ok"
        return "ok";
    }
}
