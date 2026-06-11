package m19_ddd_tactical.practice.task06;

class AuditHandler implements EventHandler {
    @Override
    public void handle(MoneyDeposited event) {
        // TODO: напечатать "[audit] +" + event.amountCents() + " на " + event.accountId()
    }
}
