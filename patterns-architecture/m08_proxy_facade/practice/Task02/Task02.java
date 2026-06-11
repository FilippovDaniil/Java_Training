package m08_proxy_facade.practice.task02;

/**
 * Задача 02 — Тема 08: Proxy (защитный, контроль доступа)
 *
 * ЗАДАНИЕ:
 *   Реализуйте защитный прокси, пропускающий вызов только при нужной роли:
 *     - интерфейс Document (файл Document.java): String read();
 *     - RealDocument (файл RealDocument.java): хранит content, read() возвращает его;
 *     - ProtectedDocument (файл ProtectedDocument.java) реализует Document,
 *       хранит RealDocument и роль пользователя userRole; read() возвращает
 *       содержимое только если userRole.equals("admin"), иначе — строку
 *       "доступ запрещён" (реальный документ при этом не читается).
 *   В main прочитайте документ от имени "admin" и от имени "guest".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   admin: секретный отчёт
 *   guest: доступ запрещён
 *
 * ТРЕБОВАНИЯ:
 *   - проверка прав живёт в прокси, не в RealDocument;
 *   - при недостаточных правах реальный объект не вызывается;
 *   - прокси и реальный объект реализуют один интерфейс Document.
 *
 * ПОДСКАЗКА:
 *   return "admin".equals(userRole) ? real.read() : "доступ запрещён";
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: создайте ProtectedDocument для "admin" и "guest", вызовите read()
    }
}
