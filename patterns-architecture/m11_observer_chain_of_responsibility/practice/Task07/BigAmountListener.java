package m11_observer_chain_of_responsibility.practice.task07;

class BigAmountListener implements TransactionListener {
    @Override
    public void onPosted(long amount, long newBalance) {
        // TODO: если Math.abs(amount) >= 50000 → напечатать "[alert] крупная сумма " + amount
    }
}
