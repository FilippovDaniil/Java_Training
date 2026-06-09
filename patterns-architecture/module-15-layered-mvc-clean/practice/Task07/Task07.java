/**
 * Задача 07 — Тема 15 (МИНИ-ПРОЕКТ BAM): слоистый банковский модуль
 *
 * Развитие BAM. Соберите операции по счёту через три слоя + DTO:
 *   - Persistence: Account (домен) + AccountRepository (интерфейс) + InMemory...;
 *   - Business: AccountService (логика и инварианты);
 *   - Presentation: AccountController (тонкий) + AccountDto (наружу).
 *
 * ЗАДАНИЕ:
 *   1. Account (файл Account.java): id, balanceCents; deposit(amount) (amount>0),
 *      withdraw(amount) (amount>0 и хватает средств, иначе IllegalStateException);
 *      getId(), getBalanceCents().
 *   2. AccountRepository (файл ...) interface findById/save; InMemoryAccountRepository.
 *   3. AccountDto (файл AccountDto.java): id, balanceCents (для отображения).
 *   4. AccountService (файл AccountService.java): зависит от репозитория;
 *      open(id), deposit(id, amount), withdraw(id, amount), view(id) → AccountDto.
 *   5. AccountController (файл AccountController.java): делегирует сервису.
 *   В main: через контроллер откройте счёт, пополните на 10000, спишите 3000,
 *   выведите DTO; попробуйте списать больше остатка (поймайте исключение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Счёт A-1: 7000
 *   Списание 99999: отказано
 *
 * ТРЕБОВАНИЯ:
 *   - вызовы строго Controller → Service → Repository (без перескоков);
 *   - инварианты баланса — в домене/сервисе, не в контроллере;
 *   - наружу отдаётся AccountDto, а не доменный Account;
 *   - сервис зависит от интерфейса репозитория (Low Coupling/DIP).
 *
 * ПОДСКАЗКА:
 *   Это тот же скелет, что в Spring-приложении BAM: @RestController → @Service →
 *   @Repository + DTO на границе. В теме 17 добавим порты/адаптеры (гексагон).
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите controller→service→InMemoryAccountRepository; open("A-1");
        //       deposit 10000; withdraw 3000; выведите view("A-1"); withdraw 99999 (поймать)
    }
}
