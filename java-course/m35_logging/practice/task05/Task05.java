package m35_logging.practice.task05;

/**
 * Задача 05 — Модуль 35: Логгеры в разных классах
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: slf4j-api + logback-classic.
 *
 * ЗАДАНИЕ:
 *   Создайте два класса (PaymentService и InventoryService), у каждого
 *   свой логгер (getLogger(СвойКласс.class)). В каждом — метод с
 *   логированием. В main вызовите оба и убедитесь, что в логах виден
 *   РАЗНЫЙ источник (имя класса-логгера).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   INFO  PaymentService - Платёж проведён: 500
 *   INFO  InventoryService - Списано со склада: товар-1
 *
 * ПОДСКАЗКА:
 *   В каждом классе: private static final Logger log =
 *       LoggerFactory.getLogger(ИмяКласса.class);
 *   В паттерне logback %logger покажет источник.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task05 {
    // Логгер для главного класса
    private static final Logger log = LoggerFactory.getLogger(Task05.class);

    public static void main(String[] args) {
        System.out.println("=== Демонстрация логгеров в разных классах ===\n");

        // Создаем экземпляры сервисов
        PaymentService paymentService = new PaymentService();
        InventoryService inventoryService = new InventoryService();

        log.info("Начало работы приложения");

        // Тестируем PaymentService
        System.out.println("\n--- PaymentService ---");
        paymentService.pay(500.0);
        paymentService.pay(15000.0);
        paymentService.pay(-100.0);
        paymentService.pay(250000.0);
        paymentService.refund(200.0);

        // Тестируем InventoryService
        System.out.println("\n--- InventoryService ---");
        inventoryService.reduce("товар-1");
        inventoryService.reduce("дефицитный-товар");
        inventoryService.reduce("товар-2");
        inventoryService.reduce("");
        inventoryService.reduce(null);
        inventoryService.checkStock("ноутбук");
        inventoryService.checkStock("дефицитный-ноутбук");

        log.info("Завершение работы приложения");

        System.out.println("\n✅ Проверьте логи: в каждой записи видно имя класса-источника");
        System.out.println("   PaymentService - для платежей");
        System.out.println("   InventoryService - для склада");
        System.out.println("   Task05 - для главного класса");
    }
}