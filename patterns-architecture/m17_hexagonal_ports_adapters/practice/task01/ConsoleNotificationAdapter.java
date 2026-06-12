package m17_hexagonal_ports_adapters.practice.task01;

// Driven-адаптер: реализует выходной порт (в проде — email/SMS).
class ConsoleNotificationAdapter implements NotificationPort {
    @Override
    public void send(String msg) {
        // TODO: напечатать "уведомление: " + msg
    }
}
