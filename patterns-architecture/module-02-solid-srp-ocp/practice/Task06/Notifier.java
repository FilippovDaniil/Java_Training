// Чистая версия: закрыта для модификации (нет switch), отправку делегирует
// каналу. Новый канал = новый класс Channel, Notifier не меняется.
class Notifier {

    // TODO: поле Channel channel + конструктор Notifier(Channel channel)

    public void notify(String text) {
        // TODO: делегировать channel.send(text)
    }
}
