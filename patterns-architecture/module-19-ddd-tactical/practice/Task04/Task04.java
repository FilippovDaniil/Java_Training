/**
 * Задача 04 — Тема 19: Factory (сборка агрегата в валидном состоянии)
 *
 * ЗАДАНИЕ:
 *   Спрячьте сложное создание счёта в фабрику, чтобы он всегда рождался валидным:
 *     - Account (файл Account.java): id, ownerId, balanceCents; deposit(amount); геттеры;
 *     - AccountFactory (файл AccountFactory.java): метод
 *       Account openNew(String ownerId, long initialDepositCents):
 *         генерирует id ("ACC-1", "ACC-2", ... через внутренний счётчик),
 *         создаёт Account, и ЕСЛИ initialDeposit > 0 — сразу пополняет.
 *   В main откройте два счёта через фабрику (один с депозитом, один без) и
 *   выведите их id и балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   ACC-1 (owner U1): 10000
 *   ACC-2 (owner U2): 0
 *
 * ТРЕБОВАНИЯ:
 *   - клиент НЕ собирает Account по шагам — это делает фабрика;
 *   - фабрика гарантирует валидное начальное состояние (правило про депозит);
 *   - id уникален (счётчик в фабрике), без обращения к времени/случайности.
 *
 * ПОДСКАЗКА:
 *   Factory — это Pure Fabrication + Creator (тема 14) в доменном масштабе.
 *   Иногда фабричный метод живёт на самом агрегате (Account.open(...)).
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: AccountFactory; openNew("U1", 10000) и openNew("U2", 0); выведите id и балансы
    }
}
