package m24_messaging_event_driven_kafka.practice.task07;

import java.util.ArrayList;
import java.util.List;

// Pub-Sub топик с изоляцией сбоев: упавший подписчик → событие в DLQ.
class OrderTopic {
    private final List<OrderSubscriber> subscribers = new ArrayList<>();
    private final List<OrderPlaced> dlq = new ArrayList<>();

    public void subscribe(OrderSubscriber subscriber) {
        // TODO: добавить подписчика
    }

    public void publish(OrderPlaced event) {
        // TODO: для каждого подписчика — try { sub.onOrder(event); }
        //       catch (Exception ex) { dlq.add(event); напечатать "в DLQ: " + event.orderId(); }
    }

    public int dlqSize() {
        // TODO: размер DLQ
        return 0;
    }
}
