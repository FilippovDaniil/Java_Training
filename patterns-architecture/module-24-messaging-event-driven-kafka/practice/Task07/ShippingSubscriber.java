class ShippingSubscriber implements OrderSubscriber {
    @Override
    public void onOrder(OrderPlaced e) {
        // TODO: напечатать "отгрузка " + e.orderId()
    }
}
