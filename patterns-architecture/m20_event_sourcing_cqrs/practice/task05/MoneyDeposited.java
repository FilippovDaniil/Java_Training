package m20_event_sourcing_cqrs.practice.task05;

// Событие (факт, прошедшее время). Неизменяемо.
record MoneyDeposited(String accountId, long amount) {
}
