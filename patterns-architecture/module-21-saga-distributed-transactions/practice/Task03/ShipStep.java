// Этот шаг падает — он запускает откат предыдущих.
class ShipStep implements SagaStep {
    @Override
    public void execute() {
        // TODO: напечатать "доставка не удалась" и бросить new RuntimeException("ship failed")
    }

    @Override
    public void compensate() {
        // TODO: (не вызовется в этом сценарии) напечатать "отмена доставки"
    }
}
