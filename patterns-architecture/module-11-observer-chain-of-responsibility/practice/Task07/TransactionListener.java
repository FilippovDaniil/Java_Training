// Observer: подписчик на событие проводки транзакции.
interface TransactionListener {
    void onPosted(long amount, long newBalance);
}
