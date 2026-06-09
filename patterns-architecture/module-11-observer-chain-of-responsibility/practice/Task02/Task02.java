/**
 * Задача 02 — Тема 11: Observer с отпиской и изменением состояния
 *
 * ЗАДАНИЕ:
 *   Метеостанция уведомляет дисплеи об изменении температуры; одного можно
 *   отписать:
 *     - интерфейс Observer (файл Observer.java): void update(int temperature);
 *     - WeatherStation (файл WeatherStation.java): subscribe/unsubscribe,
 *       setTemperature(int) меняет состояние и уведомляет подписчиков;
 *     - PhoneDisplay ("телефон: ...") и WindowDisplay ("окно: ...").
 *   В main: подпишите оба, установите температуру; отпишите окно, установите
 *   другую температуру (его уведомления уже не приходят).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   телефон: 20
 *   окно: 20
 *   (после отписки окна)
 *   телефон: 25
 *
 * ТРЕБОВАНИЯ:
 *   - setTemperature уведомляет только ТЕКУЩИХ подписчиков;
 *   - отписанный наблюдатель уведомления не получает;
 *   - субъект знает только интерфейс Observer.
 *
 * ПОДСКАЗКА:
 *   unsubscribe удаляет из списка. Чтобы избежать ConcurrentModificationException,
 *   меняйте список вне цикла уведомления.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: подпишите оба дисплея, setTemperature(20); unsubscribe(окно);
        //       setTemperature(25)
    }
}
