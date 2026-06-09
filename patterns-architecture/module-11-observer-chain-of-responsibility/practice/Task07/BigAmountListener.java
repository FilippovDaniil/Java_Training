class BigAmountListener implements TransactionListener {
    @Override
    public void onPosted(long amount, long newBalance) {
        // TODO: если Math.abs(amount) >= 50000 → напечатать "[alert] крупная сумма " + amount
    }
}
