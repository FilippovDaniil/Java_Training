package m15_layered_mvc_clean.practice.task05;

/**
 * Задача 05 — Тема 15: Clean Architecture (Dependency Rule)
 *
 * ЗАДАНИЕ:
 *   Реализуйте сценарий «узнать баланс» так, чтобы зависимости шли ВНУТРЬ, к домену:
 *     - Entity: Account (файл Account.java) — id, balanceCents; balance();
 *     - Port (внутренний интерфейс): AccountGateway (файл AccountGateway.java)
 *       Account load(String id);
 *     - Use Case: GetBalanceUseCase (файл GetBalanceUseCase.java) — зависит ТОЛЬКО
 *       от AccountGateway (порта), метод long balance(String id);
 *     - Adapter (внешний): InMemoryAccountGateway (файл ...) реализует
 *       AccountGateway, зависит ВНУТРЬ — на интерфейс.
 *   В main подключите адаптер к use case и узнайте баланс счёта.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Баланс: 15000
 *
 * ТРЕБОВАНИЯ (чек-лист Dependency Rule):
 *   - Account, AccountGateway, GetBalanceUseCase НЕ зависят от адаптера/инфраструктуры
 *     (не импортируют InMemoryAccountGateway);
 *   - адаптер реализует порт (зависимость направлена внутрь, на интерфейс);
 *   - use case получает порт извне (через конструктор), не создаёт адаптер сам.
 *
 * ПОДСКАЗКА:
 *   Порт «принадлежит» внутреннему слою (его определяет use case), а реализует
 *   его внешний адаптер — это DIP (тема 03) в масштабе архитектуры. Тема 17
 *   (Hexagonal) разовьёт эту идею.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: создайте InMemoryAccountGateway со счётом, внедрите в GetBalanceUseCase,
        //       выведите balance(id)
    }
}
