package m13_iterator_visitor.practice.task07;

interface TransactionVisitor {
    void visitDeposit(Deposit d);
    void visitWithdrawal(Withdrawal w);
}
