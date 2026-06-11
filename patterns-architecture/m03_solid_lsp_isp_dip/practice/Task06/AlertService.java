package m03_solid_lsp_isp_dip.practice.task06;

// Зависит от абстракции MessageSender. Не знает и не выбирает реализацию.
class AlertService {

    // TODO: поле MessageSender sender + конструктор AlertService(MessageSender sender)

    public void raise(String reason) {
        // TODO: sender.send("ALERT: " + reason)
    }
}
