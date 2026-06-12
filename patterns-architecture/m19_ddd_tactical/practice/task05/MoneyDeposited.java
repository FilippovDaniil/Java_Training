package m19_ddd_tactical.practice.task05;

// Domain Event: неизменяемый факт «деньги внесены» (готово).
record MoneyDeposited(String accountId, long amountCents) {
}
