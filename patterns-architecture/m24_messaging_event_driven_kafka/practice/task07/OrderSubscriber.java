package m24_messaging_event_driven_kafka.practice.task07;

interface OrderSubscriber {
    void onOrder(OrderPlaced e);
}
