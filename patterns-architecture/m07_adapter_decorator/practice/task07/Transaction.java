package m07_adapter_decorator.practice.task07;

/** Транзакция — данные (готово, менять не нужно). */
class Transaction {
    private final long amountCents;
    private final String type;

    Transaction(long amountCents, String type) {
        this.amountCents = amountCents;
        this.type = type;
    }

    long getAmountCents() { return amountCents; }
    String getType() { return type; }
}
