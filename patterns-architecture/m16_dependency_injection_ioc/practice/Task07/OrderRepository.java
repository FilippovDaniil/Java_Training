package m16_dependency_injection_ioc.practice.task07;

interface OrderRepository {
    void save(Order order);
    Order findById(String id);
}
