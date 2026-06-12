package m03_solid_lsp_isp_dip.practice.task07;

/**
 * Задача 07 — Тема 03 (МИНИ-ПРОЕКТ BAM): перевод между счетами
 *
 * Это ПЕРВЫЙ кусочек сквозного проекта BAM (Banking Account Manager).
 * Дальше он дорастёт до агрегатов, доменных событий и saga. Сейчас задача —
 * заложить ядро на SOLID: инкапсуляция денег + DIP (сервис зависит от
 * абстракции хранилища).
 *
 * ЗАДАНИЕ:
 *   1. Account (файл Account.java): инкапсулированные id и balanceCents;
 *      deposit/withdraw с инвариантами (сумма > 0; нельзя уйти в минус).
 *   2. AccountRepository (файл AccountRepository.java) — абстракция:
 *        void save(Account a);
 *        Account findById(String id);   // null, если нет
 *   3. InMemoryAccountRepository — реализация на Map (деталь).
 *   4. TransferService (файл TransferService.java): получает AccountRepository
 *      В КОНСТРУКТОРЕ; метод transfer(fromId, toId, amountCents):
 *      снять с одного, начислить другому, сохранить оба; при нехватке
 *      средств — бросить IllegalStateException и НЕ менять балансы.
 *   В main: создайте два счёта, сохраните, переведите сумму, выведите балансы;
 *   попробуйте перевести больше, чем есть (поймайте исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   До перевода: A=10000, B=0
 *   После перевода 3000: A=7000, B=3000
 *   Перевод 99999: отказано (недостаточно средств)
 *
 * ТРЕБОВАНИЯ:
 *   - TransferService зависит только от интерфейса AccountRepository
 *     (никакого 'new InMemoryAccountRepository()' внутри сервиса);
 *   - balanceCents строго инкапсулирован, меняется только через deposit/withdraw;
 *   - при неуспешном переводе ни один баланс не должен измениться.
 *
 * ПОДСКАЗКА:
 *   Сначала проверьте достаточность средств (findById + withdraw кинет
 *   исключение, если не хватает), затем deposit получателю и save обоих.
 *   Абстрактный репозиторий позже (тема 17) заменится на адаптер БД без
 *   правок TransferService.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: создайте AccountRepository (InMemory), два Account, сохраните;
        //       выполните перевод; выведите балансы; проверьте отказ при нехватке
    }
}
