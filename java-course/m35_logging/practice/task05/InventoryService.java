package m35_logging.practice.task05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class InventoryService {
    // Свой логгер для InventoryService
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    public void reduce(String product) {
        log.debug("Попытка списания товара со склада: {}", product);

        // Имитация проверки наличия товара
        if (product == null || product.trim().isEmpty()) {
            log.warn("Попытка списания товара с пустым названием");
            return;
        }

        // Имитация списания товара
        log.info("Списано со склада: {}", product);

        // Дополнительная информация
        if (product.contains("дефицитный")) {
            log.warn("Списание дефицитного товара: {}", product);
        }

        log.debug("Товар {} успешно списан", product);
    }

    // Дополнительный метод для демонстрации
    public void checkStock(String product) {
        log.info("Проверка наличия товара: {}", product);
        // Имитация проверки
        boolean inStock = Math.random() > 0.3;
        if (inStock) {
            log.debug("Товар {} в наличии", product);
        } else {
            log.warn("Товара {} нет на складе", product);
        }
    }
}
