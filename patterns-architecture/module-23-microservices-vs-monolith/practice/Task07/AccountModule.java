interface AccountModule {
    void debit(String accountId, long amount);
    long balance(String accountId);
}
