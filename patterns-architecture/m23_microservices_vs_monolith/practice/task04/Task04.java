package m23_microservices_vs_monolith.practice.task04;

/**
 * Задача 04 — Тема 23: API Gateway / фасад над несколькими модулями
 *
 * ЗАДАНИЕ:
 *   Шлюз предоставляет клиенту единый вход, скрывая обращения к нескольким модулям:
 *     - AccountService (файл ...): long balance(String id); LocalAccountService
 *       со скрытым хранилищем + seed(id, balance);
 *     - AuditService (файл ...): void record(String msg); ConsoleAuditService
 *       печатает "[audit] msg";
 *     - ApiGateway (файл ApiGateway.java): зависит от обоих сервисов; метод
 *       long viewBalance(String id) — возвращает баланс И пишет в аудит
 *       "просмотр баланса <id>".
 *   В main: наполните счёт, через шлюз запросите баланс (увидите аудит и значение).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [audit] просмотр баланса A-1
 *   Баланс: 10000
 *
 * ТРЕБОВАНИЯ:
 *   - клиент обращается только к ApiGateway, не к отдельным модулям;
 *   - шлюз координирует вызовы AccountService + AuditService;
 *   - шлюз зависит от интерфейсов сервисов (не от реализаций).
 *
 * ПОДСКАЗКА:
 *   Это Facade (тема 08) на уровне сервисов: единая точка входа, прячущая, сколько
 *   модулей задействовано. В микросервисах роль шлюза играет API Gateway.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: соберите ApiGateway(LocalAccountService с seed, ConsoleAuditService);
        //       viewBalance("A-1")
    }
}
