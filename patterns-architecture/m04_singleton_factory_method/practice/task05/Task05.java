package m04_singleton_factory_method.practice.task05;

/**
 * Задача 05 — Тема 04: Factory Method (канонический GoF — решает подкласс)
 *
 * ЗАДАНИЕ:
 *   Реализуйте классический Factory Method, где создание продукта делегировано
 *   подклассу:
 *     - интерфейс Transport (файл Transport.java): String deliver();
 *       реализации Truck и Ship;
 *     - абстрактный класс Logistics (файл Logistics.java):
 *         abstract Transport createTransport();        // фабричный метод
 *         String planDelivery() { ... createTransport().deliver() ... } // общая логика
 *     - RoadLogistics создаёт Truck, SeaLogistics создаёт Ship.
 *   В main вызовите planDelivery() у обоих логистических подклассов.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   План: доставка по дороге
 *   План: доставка по морю
 *
 * ТРЕБОВАНИЯ:
 *   - planDelivery() живёт в базовом Logistics и НЕ переопределяется;
 *   - какой Transport создать — решает подкласс через createTransport();
 *   - базовый класс работает с типом Transport, не зная конкретики.
 *
 * ПОДСКАЗКА:
 *   Отличие от Task04: там фабрика — отдельный класс со switch; здесь
 *   «развилка» вынесена в иерархию — каждый подкласс знает только свой продукт.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: создайте RoadLogistics и SeaLogistics, вызовите planDelivery()
    }
}
