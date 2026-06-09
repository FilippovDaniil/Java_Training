abstract class Approver {
    protected Approver next;

    public Approver setNext(Approver next) {
        this.next = next;
        return next;
    }

    public abstract String approve(int amount);

    protected String passToNext(int amount) {
        // TODO: next != null → next.approve(amount); иначе "не согласовано"
        return "не согласовано";
    }
}
