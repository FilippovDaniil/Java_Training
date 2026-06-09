// Driven-адаптер: реализует выходной порт (в проде — email/SMS).
class ConsoleNotificationAdapter implements NotificationPort {
    @Override
    public void send(String msg) {
        // TODO: напечатать "уведомление: " + msg
    }
}
