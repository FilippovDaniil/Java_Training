package m20_event_sourcing_cqrs.practice.task07;

// Read-модель: текущая сводка заказа как проекция лога.
class OrderProjection {
    private String orderId = "";
    private int itemCount = 0;
    private long totalCents = 0;
    private boolean paid = false;

    public void on(Object event) {
        // TODO: OrderCreated → orderId; ItemAdded → itemCount++, totalCents += priceCents();
        //       OrderPaid → paid = true
    }

    public String summary() {
        // TODO: "Заказ " + orderId + ": позиций " + itemCount + ", сумма " + totalCents
        //        + ", оплачен " + paid
        return "";
    }
}
