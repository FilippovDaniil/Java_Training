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
    public static void main(String[] args) {
        // Вызовите методы PaymentService и InventoryService
    }
}

class PaymentService {
    // TODO: свой логгер + метод pay(double amount) с логированием
}

class InventoryService {
    // TODO: свой логгер + метод reduce(String product) с логированием
}
