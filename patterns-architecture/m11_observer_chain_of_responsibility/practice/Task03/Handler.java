package m11_observer_chain_of_responsibility.practice.task03;

abstract class Handler {
    protected Handler next;

    // возвращает переданный обработчик, чтобы строить цепочку «в строчку»
    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public abstract String handle(int n);

    protected String passToNext(int n) {
        // TODO: если next != null → next.handle(n); иначе "никто не обработал"
        return "никто не обработал";
    }
}
