package m13_iterator_visitor.practice.task07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Iterable-история транзакций (Iterator).
class TransactionHistory implements Iterable<Transaction> {
    private final List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction t) {
        // TODO: добавить в transactions
    }

    @Override
    public Iterator<Transaction> iterator() {
        // TODO: вернуть transactions.iterator()
        return null;
    }
}
