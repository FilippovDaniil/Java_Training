package m19_ddd_tactical.practice.task01;

/**
 * Задача 01 — Тема 19: Repository (коллекция агрегатов)
 *
 * ЗАДАНИЕ:
 *   Реализуйте репозиторий для агрегата Account (один репозиторий — на агрегат):
 *     - Account (файл Account.java): id, balanceCents; deposit(amount); getId(); balance();
 *     - AccountRepository (файл AccountRepository.java) — интерфейс в терминах
 *       домена: Account findById(String id); void save(Account account);
 *     - InMemoryAccountRepository (файл ...) — реализация на Map (деталь хранения).
 *   В main создайте счёт, пополните, сохраните; найдите по id и выведите баланс.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс A-1: 5000
 *
 * ТРЕБОВАНИЯ:
 *   - интерфейс репозитория сформулирован в доменных терминах (findById/save),
 *     не «select/insert»;
 *   - репозиторий работает с целым агрегатом (Account), не с его кусочками;
 *   - реализация (хранилище) скрыта за интерфейсом.
 *
 * ПОДСКАЗКА:
 *   Repository — это «коллекция» агрегатов: снаружи не видно, БД это или Map.
 *   Один репозиторий на корень агрегата.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: создайте Account, deposit, save в репозиторий; findById; выведите баланс
    }
}
