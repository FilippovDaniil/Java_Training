/**
 * Задача 03 — Тема 25: проверка соглашения именования
 *
 * ЗАДАНИЕ:
 *   Правило: все классы-репозитории должны оканчиваться на "Repository".
 *     - NamingChecker (файл NamingChecker.java): List<String> check(List<String>
 *       repositoryClassNames) — для каждого имени, НЕ оканчивающегося на
 *       "Repository", добавляет "<имя> не оканчивается на Repository".
 *   В main: проверьте список ["AccountRepository", "OrderDao", "ClientRepository"];
 *   выведите нарушения.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Нарушений: 1
 *   - OrderDao не оканчивается на Repository
 *
 * ТРЕБОВАНИЯ:
 *   - правило формализовано как функция над списком имён;
 *   - нарушение — понятное сообщение (что и почему);
 *   - корректные имена нарушений не дают.
 *
 * ПОДСКАЗКА:
 *   name.endsWith("Repository"). Такие проверки имён/расположения ArchUnit делает
 *   на реальных классах; здесь — на их именах.
 */

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // TODO: NamingChecker.check(List.of("AccountRepository","OrderDao","ClientRepository"))
    }
}
