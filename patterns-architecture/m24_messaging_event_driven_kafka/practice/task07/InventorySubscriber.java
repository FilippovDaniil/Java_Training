package m24_messaging_event_driven_kafka.practice.task07;

class InventorySubscriber implements OrderSubscriber {
    @Override
    public void onOrder(OrderPlaced e) {
        // TODO: если e.sku() == "OUT" → throw new RuntimeException("нет на складе");
        //       иначе напечатать "резерв " + e.sku()
    }
}
