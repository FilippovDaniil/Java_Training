// Интерфейс, которого ожидает наш клиент.
interface PaymentGateway {
    boolean pay(long amountCents);
}
