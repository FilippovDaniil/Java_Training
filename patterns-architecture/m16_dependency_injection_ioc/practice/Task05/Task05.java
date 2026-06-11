package m16_dependency_injection_ioc.practice.task05;

/**
 * Задача 05 — Тема 16: Service Locator (и почему DI обычно лучше)
 *
 * ЗАДАНИЕ:
 *   Реализуйте Service Locator — глобальный реестр, из которого объект САМ
 *   достаёт зависимость (в отличие от DI, где её передают снаружи):
 *     - ServiceLocator (файл ServiceLocator.java): статический Map<Class<?>,Object>;
 *       static <T> void register(Class<T> type, T instance);
 *       static <T> T get(Class<T> type);
 *     - Notifier (файл Notifier.java): void notify(String msg); EmailNotifier
 *       печатает "email: msg";
 *     - OrderProcessor (файл OrderProcessor.java): в методе process() САМ берёт
 *       Notifier из ServiceLocator.get(Notifier.class) и шлёт уведомление.
 *   В main зарегистрируйте EmailNotifier в локаторе, затем создайте
 *   OrderProcessor и вызовите process().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   email: заказ обработан
 *
 * ТРЕБОВАНИЯ:
 *   - OrderProcessor получает зависимость через ServiceLocator.get(...), а не
 *     через конструктор;
 *   - локатор хранит зарегистрированные реализации по типу;
 *   - ОБРАТИТЕ ВНИМАНИЕ (см. подсказку): зависимость теперь СКРЫТА — её не видно
 *     в сигнатуре OrderProcessor.
 *
 * ПОДСКАЗКА:
 *   Минусы Service Locator vs DI: зависимость спрятана (не видна в конструкторе),
 *   тесты требуют подмены глобального состояния локатора, появляется неявная
 *   связь с ним. Поэтому DI обычно предпочтительнее; SL оправдан редко.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: ServiceLocator.register(Notifier.class, new EmailNotifier());
        //       new OrderProcessor().process()
    }
}
