package m17_hexagonal_ports_adapters.practice.task07;

/**
 * Задача 07 — Тема 17 (МИНИ-ПРОЕКТ BAM): перевод в гексагональной архитектуре
 *
 * Развитие BAM. Перевод между счетами (он же был в теме 03) теперь оформлен как
 * полный гексагон: driving-адаптер → входной порт → ядро → выходной порт → driven-адаптер.
 *
 * ЗАДАНИЕ:
 *   1. Account (файл Account.java): id, balanceCents; deposit(amount) (amount>0),
 *      withdraw(amount) (amount>0 и хватает средств, иначе IllegalStateException);
 *      id(), balance().
 *   2. Выходной порт AccountRepository (файл ...): load(id), save(account);
 *      driven-адаптер InMemoryAccountRepository.
 *   3. Входной порт TransferUseCase (файл ...): void transfer(String fromId,
 *      String toId, long amount).
 *   4. ЯДРО TransferService (файл TransferService.java): реализует TransferUseCase,
 *      зависит от AccountRepository; transfer → load обоих, withdraw у отправителя,
 *      deposit получателю, save обоих.
 *   5. Driving-адаптер ConsoleTransferAdapter (файл ...): зовёт useCase.transfer(...)
 *      и печатает результат.
 *   В main (composition root): создайте репозиторий, положите 2 счёта (A=10000, B=0),
 *   соберите ядро и driving-адаптер, переведите 3000 A→B, выведите балансы.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Перевод 3000: A→B
 *   A=7000, B=3000
 *
 * ТРЕБОВАНИЯ:
 *   - ядро (TransferService) зависит только от портов, не от адаптеров;
 *   - инварианты баланса — в Account (домен), не в адаптерах;
 *   - сборка адаптеров с ядром — в main (DI, composition root);
 *   - заменить хранилище (на БД) можно новым адаптером AccountRepository без правок ядра.
 *
 * ПОДСКАЗКА:
 *   Сравните с module-03/Task07: логика та же, но теперь явно выделены входной
 *   порт (TransferUseCase), выходной порт (AccountRepository) и адаптеры. В теме 25
 *   ArchUnit проверит, что ядро не зависит от адаптеров.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: composition root — соберите InMemoryAccountRepository (с A=10000, B=0),
        //       TransferService, ConsoleTransferAdapter; переведите 3000 A→B; выведите балансы
    }
}
