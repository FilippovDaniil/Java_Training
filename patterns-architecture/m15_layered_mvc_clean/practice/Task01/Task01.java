package m15_layered_mvc_clean.practice.task01;

/**
 * Задача 01 — Тема 15: Многослойная архитектура (3 слоя)
 *
 * ЗАДАНИЕ:
 *   Реализуйте операцию через три слоя, где вызовы идут СВЕРХУ ВНИЗ:
 *     - Persistence: Client (файл Client.java) — данные id, name;
 *       ClientRepository (файл ClientRepository.java) interface findById(String);
 *       InMemoryClientRepository (файл ...) на Map;
 *     - Business: ClientService (файл ClientService.java) — describe(id):
 *       найти клиента и вернуть "Клиент: <name>" (или "не найден");
 *     - Presentation: ClientController (файл ClientController.java) — show(id)
 *       делегирует сервису.
 *   В main соберите цепочку controller → service → repository, выведите
 *   описание существующего и несуществующего клиента.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Клиент: Аня
 *   не найден
 *
 * ТРЕБОВАНИЯ:
 *   - контроллер обращается к сервису, сервис — к репозиторию (без перескоков
 *     контроллер → репозиторий);
 *   - каждый слой зависит только от слоя ниже;
 *   - бизнес-логика в сервисе, не в контроллере.
 *
 * ПОДСКАЗКА:
 *   Это «скелет» типичного Spring-приложения: @Controller → @Service → @Repository.
 */

public class Task01 {
    public static void main(String[] args) {
        // TODO: соберите controller(service(repository)); выведите show(id) для
        //       существующего и несуществующего клиента
    }
}
