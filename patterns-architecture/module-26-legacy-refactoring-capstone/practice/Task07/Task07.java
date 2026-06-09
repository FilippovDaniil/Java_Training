/**
 * Задача 07 — Тема 26 (CAPSTONE, OPS + BAM): рефакторинг legacy в чистую архитектуру
 *
 * ФИНАЛ РАЗДЕЛА. Дан «запущенный» god-сервис оформления (LegacyCheckout, файл
 * LegacyCheckout.java) — он внутри одного метода и считает цену (OPS), и проводит
 * оплату (BAM), без границ и абстракций. Приведите его к чистой архитектуре,
 * объединив техники раздела:
 *   - HEXAGON/ПОРТЫ (тема 17): выделить порты OrderPricing (OPS) и PaymentPort (BAM);
 *   - USE CASE (тема 15): ядро CleanCheckout реализует входной порт CheckoutUseCase,
 *     зависит от выходных портов;
 *   - BRANCH BY ABSTRACTION + STRANGLER/TOGGLE (тема 26): MigrationRouter переключает
 *     трафик со старого на новое по флагу, не ломая клиента.
 *
 * ЗАДАНИЕ:
 *   1. CheckoutUseCase (файл ...): String checkout(String orderId) — входной порт.
 *   2. Выходные порты: OrderPricing (файл ...) long priceOf(String orderId);
 *      PaymentPort (файл ...) boolean charge(long amount).
 *   3. Адаптеры: InMemoryPricing (orderId → цена, через seed) и AlwaysOkPayment
 *      (charge → true).
 *   4. CleanCheckout (файл ...) implements CheckoutUseCase: зависит от OrderPricing
 *      и PaymentPort; checkout(orderId): price = priceOf(orderId); если charge(price)
 *      → "[clean] оформлен <orderId> на <price>".
 *   5. MigrationRouter (файл ...) implements CheckoutUseCase: хранит legacy и clean
 *      (оба CheckoutUseCase) и флаг useClean; checkout делегирует выбранному;
 *      setUseClean(boolean).
 *   В main: pricing с "A-1"→5000; соберите CleanCheckout(pricing, payment) и
 *   LegacyCheckout; через MigrationRouter оформите "A-1" сначала на legacy, затем,
 *   включив флаг, на clean.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [legacy] оформлен A-1
 *   [clean] оформлен A-1 на 5000
 *
 * ТРЕБОВАНИЯ:
 *   - god-логика разнесена: цена за OrderPricing, оплата за PaymentPort (OPS/BAM-границы);
 *   - ядро CleanCheckout зависит только от портов (Dependency Rule, тема 17);
 *   - переключение legacy → clean через флаг (безопасная миграция), клиент не меняется;
 *   - и legacy, и clean реализуют один контракт CheckoutUseCase (Branch by Abstraction).
 *
 * ПОДСКАЗКА:
 *   Это финал пути: принципы ООП/SOLID → паттерны → DDD/гексагон → арх-тесты →
 *   безопасный рефакторинг legacy. MigrationRouter = Strangler + Feature Toggle поверх
 *   общего порта. После миграции legacy-ветку и флаг удаляют (иначе Lava Flow, тема 22).
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: InMemoryPricing seed("A-1", 5000); CleanCheckout(pricing, payment); LegacyCheckout;
        //       MigrationRouter(legacy, clean, false) → checkout("A-1"); setUseClean(true) → checkout("A-1")
    }
}
