package m16_dependency_injection_ioc.practice.task07;

class OrderController {
    // TODO: поле final OrderService service + конструктор OrderController(OrderService service)

    public void place(String id, long amountCents) {
        // TODO: делегировать service.place(...)
    }

    public long amount(String id) {
        // TODO: делегировать service.amount(id)
        return 0;
    }
}
