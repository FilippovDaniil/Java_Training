// Цепочка обработчиков (CoR).
abstract class Handler {
    protected Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(int level, String msg);

    protected void passToNext(int level, String msg) {
        // TODO: если next != null → next.handle(level, msg)
    }
}
