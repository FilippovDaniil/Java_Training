package m24_messaging_event_driven_kafka.practice.task07;

class ShippingSubscriber implements OrderSubscriber {
    @Override
    public void onOrder(OrderPlaced e) {
        // TODO: напечатать "отгрузка " + e.orderId()
    }
}
