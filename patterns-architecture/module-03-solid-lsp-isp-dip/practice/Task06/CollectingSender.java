// «Тестовый двойник»: не отправляет наружу, а копит сообщения для проверки.
class CollectingSender implements MessageSender {
    // TODO: приватный List<String> sent

    @Override
    public void send(String msg) {
        // TODO: добавить msg в список
    }

    public int sentCount() {
        // TODO: размер списка
        return 0;
    }
}
