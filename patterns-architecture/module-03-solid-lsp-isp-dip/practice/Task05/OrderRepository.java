import java.util.List;

// Абстракция принадлежит бизнес-слою. Детали (InMemory, БД) реализуют её.
interface OrderRepository {
    List<Long> findAllAmountsCents();
}
