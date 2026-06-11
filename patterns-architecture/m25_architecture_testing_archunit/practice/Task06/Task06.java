package m25_architecture_testing_archunit.practice.task06;

/**
 * Задача 06 — Тема 25: набор архитектурных правил (suite)
 *
 * ЗАДАНИЕ:
 *   Объедините несколько правил в один «архитектурный тест»:
 *     - ArchRule (файл ArchRule.java): List<String> check(Map<String,Set<String>> deps);
 *     - DomainPureRule (файл ...): domain должен иметь ПУСТЫЕ зависимости, иначе
 *       "domain не должен ни от чего зависеть";
 *     - NoWebInServiceRule (файл ...): service не должен зависеть от "web", иначе
 *       "service зависит от web";
 *     - ArchTestSuite (файл ArchTestSuite.java): add(ArchRule); runAll(deps)
 *       прогоняет все правила и возвращает ОБЩИЙ список нарушений.
 *   В main: граф domain→{}, service→{"web"}; прогоните suite из двух правил;
 *   выведите число нарушений и их список.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Нарушений: 1
 *   - service зависит от web
 *
 * ТРЕБОВАНИЯ:
 *   - правила единообразны (общий интерфейс ArchRule);
 *   - suite агрегирует нарушения всех правил;
 *   - пустой общий список = архитектурный тест пройден (зелёная сборка).
 *
 * ПОДСКАЗКА:
 *   Так в реальном проекте набор ArchUnit-правил гоняется одним тест-классом в CI.
 *   Каждое правило независимо, suite собирает их вердикты.
 */

import java.util.Map;
import java.util.Set;

public class Task06 {
    public static void main(String[] args) {
        // TODO: ArchTestSuite с DomainPureRule и NoWebInServiceRule; runAll(deps); вывести нарушения
    }
}
