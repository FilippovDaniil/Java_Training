package m07_adapter_decorator.practice.task02;

/**
 * Задача 02 — Тема 07: Adapter с конвертацией единиц
 *
 * ЗАДАНИЕ:
 *   Сторонний датчик FahrenheitSensor (файл FahrenheitSensor.java, менять
 *   НЕЛЬЗЯ) отдаёт температуру в Фаренгейтах: double readFahrenheit().
 *   Наша система работает с интерфейсом TemperatureSensor (файл
 *   TemperatureSensor.java): double readCelsius(). Сделайте адаптер:
 *     - CelsiusAdapter (файл CelsiusAdapter.java) реализует TemperatureSensor,
 *       внутри держит FahrenheitSensor и переводит °F → °C
 *       по формуле C = (F - 32) * 5 / 9.
 *   В main снимите показание через TemperatureSensor (в Цельсиях).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Фаренгейт: 212.0
 *   Цельсий: 100.0
 *
 * ТРЕБОВАНИЯ:
 *   - FahrenheitSensor не изменяется;
 *   - вся «магия» — только конвертация единиц (без иной логики);
 *   - клиент работает с TemperatureSensor, не зная про Фаренгейты.
 *
 * ПОДСКАЗКА:
 *   Адаптер часто именно конвертирует представление данных под ожидаемый
 *   интерфейс. Формула: (F - 32) * 5.0 / 9.0.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: оберните FahrenheitSensor в CelsiusAdapter, прочитайте readCelsius()
    }
}
