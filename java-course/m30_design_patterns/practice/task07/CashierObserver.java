package m30_design_patterns.practice.task07;

class CashierObserver implements OrderObserver {
    private String name;

    public CashierObserver(String name) {
        this.name = name;
    }

    @Override
    public void onNewOrder(String description, double price) {
        System.out.println("   💰 Кассир " + name + " принял заказ: " + description);
        System.out.println("   💰 Сумма к оплате: " + String.format("%.2f", price) + " руб");
        System.out.println("   ✅ Кассир " + name + " оформил заказ!");
    }
}