/**
 * Задача 06 — Тема 17: несколько выходных портов у одного ядра
 *
 * ЗАДАНИЕ:
 *   Ядро может зависеть от НЕСКОЛЬКИХ выходных портов. Реализуйте списание со
 *   счёта с записью в аудит:
 *     - Account (файл Account.java): id, balanceCents; withdraw(amount); balance(); id();
 *     - выходной порт AccountRepository (файл ...): load/save; адаптер InMemoryAccountRepository;
 *     - выходной порт AuditPort (файл ...): void record(String msg); адаптер ConsoleAuditAdapter
 *       печатает "[audit] msg";
 *     - ЯДРО WithdrawService (файл WithdrawService.java): зависит от ОБОИХ портов;
 *       withdraw(id, amount) → load, account.withdraw(amount), save, audit.record(
 *       "списано " + amount + " со счёта " + id).
 *   В main: счёт "A" с 10000; спишите 3000; проверьте аудит и баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] списано 3000 со счёта A
 *   Баланс A: 7000
 *
 * ТРЕБОВАНИЯ:
 *   - ядро зависит от двух портов (репозиторий + аудит), оба внедрены через конструктор;
 *   - адаптеры реализуют свои порты; ядро о конкретных адаптерах не знает;
 *   - доменная операция (withdraw) — в Account/ядре, не в адаптерах.
 *
 * ПОДСКАЗКА:
 *   Каждый внешний эффект (хранение, аудит, отправка) — отдельный выходной порт.
 *   Это сохраняет ядро чистым и заменяемость адаптеров.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: соберите WithdrawService(InMemoryAccountRepository, ConsoleAuditAdapter);
        //       положите счёт A=10000; withdraw("A", 3000); выведите баланс
    }
}
