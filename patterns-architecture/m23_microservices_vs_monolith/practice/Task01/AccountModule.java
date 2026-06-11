package m23_microservices_vs_monolith.practice.task01;

// Публичный API модуля «Счета».
interface AccountModule {
    long balance(String accountId);
}
