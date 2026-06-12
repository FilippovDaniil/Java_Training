package m16_dependency_injection_ioc.practice.task06;

/**
 * Задача 06 — Тема 16: DI ради тестируемости (подмена двойником)
 *
 * ЗАДАНИЕ:
 *   Покажите, как DI облегчает тесты: вместо реальной зависимости подставляем
 *   «тестового двойника».
 *     - интерфейс PaymentGateway (файл PaymentGateway.java): boolean charge(long amount);
 *     - RealPaymentGateway (файл ...): charge возвращает amount > 0 (как «боевой»);
 *     - FakePaymentGateway (файл ...): НЕ платит реально, но запоминает число
 *       вызовов; chargeCount(); charge возвращает true;
 *     - PaymentService (файл PaymentService.java): PaymentGateway через конструктор;
 *       pay(amount) делегирует gateway.charge(amount).
 *   В main: создайте PaymentService с FakePaymentGateway, проведите 2 платежа и
 *   проверьте, что fake зафиксировал 2 вызова (без реальной оплаты).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Платёж 1 ok: true
 *   Платёж 2 ok: true
 *   Вызовов charge в тесте: 2
 *
 * ТРЕБОВАНИЯ:
 *   - PaymentService не знает, реальный это шлюз или fake (зависит от интерфейса);
 *   - FakePaymentGateway позволяет проверить поведение сервиса без боевой оплаты;
 *   - подмена возможна именно благодаря DI (зависимость передаётся снаружи).
 *
 * ПОДСКАЗКА:
 *   Тестовые двойники (mock/fake) подставляются вместо реальных зависимостей —
 *   это прямое следствие DI. В тестах роль «сборщика» играет сам тест.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: PaymentService с FakePaymentGateway; pay дважды; вывести chargeCount()
    }
}
