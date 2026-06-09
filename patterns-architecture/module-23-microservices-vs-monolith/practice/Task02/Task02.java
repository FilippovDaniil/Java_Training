/**
 * Задача 02 — Тема 23: связь модулей через публичный API
 *
 * ЗАДАНИЕ:
 *   Модуль «Платежи» зависит от модуля «Счета» ТОЛЬКО через его интерфейс:
 *     - AccountModule (файл AccountModule.java): long balance(String id);
 *       AccountModuleImpl со скрытым хранилищем + seed(id, balance);
 *     - PaymentModule (файл PaymentModule.java): зависит от AccountModule
 *       (конструктор); метод boolean canPay(String accountId, long amount) →
 *       balance(accountId) >= amount.
 *   В main: наполните счёт, проверьте возможность оплаты в пределах и сверх баланса.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Оплата 5000 возможна: true
 *   Оплата 99999 возможна: false
 *
 * ТРЕБОВАНИЯ:
 *   - PaymentModule не лезет во внутренности AccountModule — только публичный API;
 *   - зависимость на интерфейс AccountModule (Low Coupling);
 *   - модули общаются строго через контракт.
 *
 * ПОДСКАЗКА:
 *   Так модули монолита остаются развязанными: позже AccountModule можно вынести
 *   в отдельный сервис, а PaymentModule переключить на сетевой клиент того же контракта.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: AccountModuleImpl + seed; PaymentModule(accounts); canPay в пределах и сверх баланса
    }
}
