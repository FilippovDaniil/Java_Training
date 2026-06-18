package m30_design_patterns.practice.task06;

class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        // Симулируем долгую загрузку
        System.out.println("   ⏳ Загрузка '" + fileName + "' с диска...");
        try {
            Thread.sleep(500); // Имитация загрузки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("   ✅ Загрузка '" + fileName + "' завершена");
    }

    @Override
    public void display() {
        System.out.println("   🖼️ Показ '" + fileName + "'");
    }
}
