package m30_design_patterns.practice.task07;

class ManagerObserver implements OrderObserver {
    private String name;

    public ManagerObserver(String name) {
        this.name = name;
    }

    @Override
    public void onNewOrder(String description, double price) {
        System.out.println("   📋 Менеджер " + name + " зарегистрировал заказ: " + description);
        System.out.println("   📋 Статистика: заказ на сумму " + String.format("%.2f", price) + " руб");
    }
}
