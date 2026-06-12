package m20_event_sourcing_cqrs.practice.task05;

// Команда (намерение, повелительное наклонение). Может быть отклонена.
record DepositCommand(String accountId, long amount) {
}
