package m25_architecture_testing_archunit.practice.task04;

/**
 * Задача 04 — Тема 25: запрет нежелательной зависимости
 *
 * ЗАДАНИЕ:
 *   Правило: указанный компонент не должен зависеть от запрещённого пакета
 *   (например, domain не должен зависеть от "java.sql").
 *     - ForbiddenDependencyChecker (файл ...): List<String> check(
 *       Map<String,Set<String>> deps, String from, String forbidden):
 *       если deps[from] содержит forbidden → вернуть
 *       ["<from> зависит от запрещённого <forbidden>"]; иначе пусто.
 *   В main: граф, где domain зависит от "java.sql"; проверьте правило
 *   (from="domain", forbidden="java.sql").
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Нарушений: 1
 *   - domain зависит от запрещённого java.sql
 *
 * ТРЕБОВАНИЯ:
 *   - проверка параметризована (любой from / forbidden);
 *   - возвращает нарушение с понятным сообщением;
 *   - если зависимости нет — список пуст.
 *
 * ПОДСКАЗКА:
 *   Так формализуют «domain не импортирует Spring/JDBC» (темы 17–18). ArchUnit
 *   делает это правилом noClasses().that()...should().dependOnClassesThat()...
 */

import java.util.Map;
import java.util.Set;

public class Task04 {
    public static void main(String[] args) {
        // TODO: deps с domain→{"java.sql","model"}; check(deps, "domain", "java.sql")
    }
}
