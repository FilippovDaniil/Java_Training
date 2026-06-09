interface TransactionVisitor {
    void visitDeposit(Deposit d);
    void visitWithdrawal(Withdrawal w);
}
