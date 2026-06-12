package m20_event_sourcing_cqrs.practice.task04;

// Проекция: собирает read-представление (статистику) из потока событий.
class OrderStatsProjection {
    private int placed = 0;
    private int shipped = 0;

    public void on(Object event) {
        // TODO: OrderPlaced → placed++; OrderShipped → shipped++
    }

    public int placed() {
        // TODO
        return placed;
    }

    public int shipped() {
        // TODO
        return shipped;
    }
}
