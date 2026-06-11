package m10_strategy_command.practice.task01;

// Контекст: хранит стратегию и делегирует ей расчёт.
class Checkout {
    // TODO: поле DiscountStrategy strategy

    public void setStrategy(DiscountStrategy strategy) {
        // TODO: сохранить стратегию
    }

    public long total(long priceCents) {
        // TODO: делегировать strategy.apply(priceCents)
        return priceCents;
    }
}
