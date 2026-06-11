package m11_observer_chain_of_responsibility.practice.task07;

// Observer: подписчик на событие проводки транзакции.
interface TransactionListener {
    void onPosted(long amount, long newBalance);
}
