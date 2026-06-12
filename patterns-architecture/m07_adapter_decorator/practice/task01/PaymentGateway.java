package m07_adapter_decorator.practice.task01;

// Интерфейс, которого ожидает наш клиент.
interface PaymentGateway {
    boolean pay(long amountCents);
}
