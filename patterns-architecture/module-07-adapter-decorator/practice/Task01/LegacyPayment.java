// Чужой класс (готов, менять НЕЛЬЗЯ). Интерфейс «неудобный» для нашего клиента.
class LegacyPayment {
    void doPayment(int cents) {
        System.out.println("оплачено " + cents);
    }
}
