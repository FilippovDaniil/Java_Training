import java.util.List;

class InMemoryOrderRepository implements OrderRepository {
    // TODO: приватное поле List<Long> с суммами + конструктор, принимающий суммы

    @Override
    public List<Long> findAllAmountsCents() {
        // TODO: вернуть суммы
        return List.of();
    }
}
