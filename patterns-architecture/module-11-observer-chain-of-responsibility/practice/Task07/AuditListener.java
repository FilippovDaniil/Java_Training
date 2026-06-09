class AuditListener implements TransactionListener {
    @Override
    public void onPosted(long amount, long newBalance) {
        // TODO: напечатать "[audit] проведено " + amount + ", баланс " + newBalance
    }
}
