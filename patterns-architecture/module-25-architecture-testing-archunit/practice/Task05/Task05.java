/**
 * Задача 05 — Тема 25: правило доступа «только X может обращаться к Y»
 *
 * ЗАДАНИЕ:
 *   Правило: к компоненту Y разрешено обращаться только компоненту X; все
 *   остальные зависимости на Y — нарушение.
 *     - AccessChecker (файл AccessChecker.java): List<String> check(
 *       Map<String,Set<String>> deps, String target, String allowedAccessor):
 *       для каждого компонента c, который зависит от target и c != allowedAccessor,
 *       добавить "<c> обращается к <target> (разрешено только <allowedAccessor>)".
 *   В main: граф, где и "service", и "web" зависят от "database"; правило —
 *   к "database" может обращаться только "service".
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Нарушений: 1
 *   - web обращается к database (разрешено только service)
 *
 * ТРЕБОВАНИЯ:
 *   - проверяются ВСЕ компоненты графа, зависящие от target;
 *   - разрешённый компонент нарушением не считается;
 *   - сообщение указывает, кто нарушил и что разрешено.
 *
 * ПОДСКАЗКА:
 *   Переберите entrySet() графа: если value содержит target и key != allowedAccessor —
 *   это нарушение. Так стерегут «к БД ходит только persistence».
 */

import java.util.Map;
import java.util.Set;

public class Task05 {
    public static void main(String[] args) {
        // TODO: deps service→{database}, web→{database}; check(deps, "database", "service")
    }
}
