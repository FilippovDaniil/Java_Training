package m17_hexagonal_ports_adapters.practice.task01;

// Выходной (driven) порт: что ядру нужно от внешнего мира.
interface NotificationPort {
    void send(String msg);
}
