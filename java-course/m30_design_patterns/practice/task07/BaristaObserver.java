package m30_design_patterns.practice.task07;

class BaristaObserver implements OrderObserver {
    private String name;

    public BaristaObserver(String name) {
        this.name = name;
    }

    @Override
    public void onNewOrder(String description, double price) {
        System.out.println("   👨‍🍳 Бариста " + name + " получил заказ: " + description);
        System.out.println("   👨‍🍳 Начинает приготовление...");
        try {
            Thread.sleep(200); // Имитация приготовления
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   ✅ Бариста " + name + " завершил приготовление!");
    }
}

