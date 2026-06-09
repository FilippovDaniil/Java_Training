// Выходной (driven) порт: что ядру нужно от внешнего мира.
interface NotificationPort {
    void send(String msg);
}
