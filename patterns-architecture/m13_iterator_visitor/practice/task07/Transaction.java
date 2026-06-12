package m13_iterator_visitor.practice.task07;

interface Transaction {
    void accept(TransactionVisitor v);
}
