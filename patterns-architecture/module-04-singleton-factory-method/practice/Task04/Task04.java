/**
 * Задача 04 — Тема 04: Factory Method (статическая фабрика)
 *
 * ЗАДАНИЕ:
 *   Скройте выбор конкретного класса транспорта за фабрикой:
 *     - интерфейс Transport (файл Transport.java): String deliver();
 *     - Truck ("доставка по дороге") и Ship ("доставка по морю");
 *     - TransportFactory (файл TransportFactory.java): static Transport
 *       create(String type) — по "truck"/"ship" создаёт нужную реализацию,
 *       иначе IllegalArgumentException.
 *   В main создайте оба транспорта ЧЕРЕЗ фабрику (не через new) и вызовите
 *   deliver().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   доставка по дороге
 *   доставка по морю
 *   Неизвестный тип plane: отказ
 *
 * ТРЕБОВАНИЯ:
 *   - клиент (main) не вызывает new Truck()/new Ship() напрямую — только фабрику;
 *   - фабрика возвращает тип Transport (абстракцию), не конкретный класс;
 *   - неизвестный тип → исключение (поймайте его в main).
 *
 * ПОДСКАЗКА:
 *   switch по type внутри фабрики допустим для начала. В Task06 вы замените его
 *   реестром, чтобы не нарушать OCP.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Transport через TransportFactory.create("truck"/"ship"),
        //       вызовите deliver(); попробуйте неизвестный тип (поймайте исключение)
    }
}
