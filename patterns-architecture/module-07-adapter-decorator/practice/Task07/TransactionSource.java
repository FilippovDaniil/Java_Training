import java.util.List;

// Интерфейс, ожидаемый нашей системой. Его реализуют и адаптер, и декоратор.
interface TransactionSource {
    List<Transaction> load();
}
