/**
 * Задача 07 — Тема 19 (МИНИ-ПРОЕКТ BAM): тактический DDD в сборе
 *
 * Развитие BAM. Соберите все тактические блоки вокруг агрегата Account:
 *   AGGREGATE + REPOSITORY + DOMAIN SERVICE + DOMAIN EVENTS.
 *
 * ЗАДАНИЕ:
 *   1. События: MoneyDeposited и MoneyWithdrawn (файлы ...) — records
 *      (accountId, amountCents) (готовы).
 *   2. Account (файл Account.java) — агрегат: id, balanceCents; deposit/withdraw
 *      (с инвариантами) меняют баланс И записывают соответствующее событие;
 *      pullEvents() возвращает копию событий (List<Object>) и очищает; balance(), getId().
 *   3. AccountRepository (файл ...) + InMemoryAccountRepository.
 *   4. TransferService (файл TransferService.java) — доменный сервис:
 *      transfer(fromId, toId, amount) координирует перевод через репозиторий.
 *   В main: счета A=10000, B=0; переведите 3000 A→B; выведите балансы и события,
 *   накопленные на каждом счёте (A — списание, B — зачисление).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   A=7000, B=3000
 *   События A: [MoneyWithdrawn[accountId=A, amountCents=3000]]
 *   События B: [MoneyDeposited[accountId=B, amountCents=3000]]
 *
 * ТРЕБОВАНИЯ:
 *   - инварианты и запись событий — внутри агрегата Account;
 *   - перевод (логика между агрегатами) — в доменном сервисе;
 *   - хранение — через репозиторий (один на агрегат);
 *   - события неизменяемы и названы в прошедшем времени.
 *
 * ПОДСКАЗКА:
 *   События records дают аккуратный toString автоматически. В теме 20 эти же
 *   события станут единственным источником истины (Event Sourcing).
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите репозиторий с A=10000, B=0; TransferService.transfer("A","B",3000);
        //       выведите балансы и pullEvents() каждого счёта
    }
}
