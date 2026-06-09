class EmailHandler implements EventHandler {
    @Override
    public void handle(MoneyDeposited event) {
        // TODO: напечатать "[email] зачислено " + event.amountCents()
    }
}
