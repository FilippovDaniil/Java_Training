interface Transaction {
    void accept(TransactionVisitor v);
}
