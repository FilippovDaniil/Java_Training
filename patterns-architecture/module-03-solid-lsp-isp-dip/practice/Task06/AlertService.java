// Зависит от абстракции MessageSender. Не знает и не выбирает реализацию.
class AlertService {

    // TODO: поле MessageSender sender + конструктор AlertService(MessageSender sender)

    public void raise(String reason) {
        // TODO: sender.send("ALERT: " + reason)
    }
}
