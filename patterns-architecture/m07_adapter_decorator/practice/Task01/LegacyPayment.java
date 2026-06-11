package m07_adapter_decorator.practice.task01;

// Чужой класс (готов, менять НЕЛЬЗЯ). Интерфейс «неудобный» для нашего клиента.
class LegacyPayment {
    void doPayment(int cents) {
        System.out.println("оплачено " + cents);
    }
}
