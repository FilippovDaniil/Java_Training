package m11_observer_chain_of_responsibility.practice.task07;

class AuditListener implements TransactionListener {
    @Override
    public void onPosted(long amount, long newBalance) {
        // TODO: напечатать "[audit] проведено " + amount + ", баланс " + newBalance
    }
}
