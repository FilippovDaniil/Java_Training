package m14_grasp.practice.task07;

// Pure Fabrication + точка для Low Coupling: абстракция хранилища.
interface OrderRepository {
    void save(Order order);
    Order findById(String id);
}
