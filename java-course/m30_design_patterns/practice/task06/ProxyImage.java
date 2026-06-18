package m30_design_patterns.practice.task06;

class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage;
    private int displayCount = 0;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
        System.out.println("📋 Создан ProxyImage для '" + fileName + "' (загрузка отложена)");
    }

    @Override
    public void display() {
        displayCount++;

        // Ленивая загрузка: создаем RealImage только при первом вызове
        if (realImage == null) {
            System.out.println("\n   🔄 Первый вызов display() - создаем RealImage...");
            realImage = new RealImage(fileName);
        } else {
            System.out.println("\n   ♻️ Используем уже загруженный RealImage (вызов #" + displayCount + ")");
        }

        realImage.display();
    }

    public int getDisplayCount() {
        return displayCount;
    }
}