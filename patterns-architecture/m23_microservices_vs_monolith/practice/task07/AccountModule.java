package m23_microservices_vs_monolith.practice.task07;

interface AccountModule {
    void debit(String accountId, long amount);
    long balance(String accountId);
}
