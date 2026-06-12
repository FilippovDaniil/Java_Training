package m19_ddd_tactical.practice.task03;

/**
 * Задача 03 — Тема 19: Domain Service (логика между агрегатами)
 *
 * ЗАДАНИЕ:
 *   Перевод затрагивает ДВА счёта — это не «дело» одного агрегата, выносим в
 *   доменный сервис:
 *     - Account (файл Account.java): id, balanceCents; deposit(amount),
 *       withdraw(amount) (хватает средств, иначе IllegalStateException); getId(); balance();
 *     - AccountRepository (файл ...) + InMemoryAccountRepository;
 *     - TransferService (файл TransferService.java): transfer(fromId, toId, amount) —
 *       найти оба счёта, withdraw у отправителя, deposit получателю, save обоих.
 *   В main: два счёта (A=10000, B=0), переведите 3000 A→B, выведите балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   A=7000, B=3000
 *
 * ТРЕБОВАНИЯ:
 *   - правила баланса (withdraw/deposit) — внутри Account (Information Expert);
 *   - сервис содержит только логику МЕЖДУ агрегатами (координацию перевода);
 *   - сервис работает с агрегатами через репозиторий.
 *
 * ПОДСКАЗКА:
 *   Если операция про один агрегат — она в нём. Если затрагивает несколько —
 *   доменный сервис. Перевод — классический пример второго.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: два счёта в репозитории; TransferService.transfer("A","B",3000); выведите балансы
    }
}
