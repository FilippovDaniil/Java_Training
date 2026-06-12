package m09_composite_bridge.practice.task07;

/**
 * Задача 07 — Тема 09 (МИНИ-ПРОЕКТ BAM): портфель счетов + формат выписки
 *
 * Развитие BAM. Объедините два структурных паттерна:
 *   - COMPOSITE — портфель из счетов и вложенных под-портфелей; общий баланс
 *     считается рекурсивно;
 *   - BRIDGE — формат вывода (plain / csv) вынесен в отдельную иерархию и
 *     комбинируется с любым узлом.
 *
 * ЗАДАНИЕ:
 *   1. AccountComponent (файл AccountComponent.java): String name();
 *      long totalBalanceCents();
 *   2. Account (файл Account.java) — лист: name + balanceCents;
 *      totalBalanceCents() = balanceCents.
 *   3. Portfolio (файл Portfolio.java) — композит: name + List<AccountComponent>,
 *      add(...), totalBalanceCents() рекурсивно суммирует.
 *   4. StatementFormat (файл StatementFormat.java) — Bridge:
 *      String format(AccountComponent node); реализации PlainFormat
 *      ("<name>: <balance>") и CsvFormat ("<name>;<balance>").
 *   В main соберите портфель с парой счетов и вложенным под-портфелем, посчитайте
 *   общий баланс (Composite) и выведите его двумя форматами (Bridge).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Клиент Иванов: 15000
 *   Клиент Иванов;15000
 *
 * ТРЕБОВАНИЯ:
 *   - totalBalanceCents() вызывается единообразно на счёте и на портфеле;
 *   - под-портфели вкладываются (рекурсивная сумма);
 *   - формат вывода не зашит в узлы — он выбирается через StatementFormat (Bridge);
 *   - добавление нового формата (например, json) не требует правок Composite-классов.
 *
 * ПОДСКАЗКА:
 *   Composite даёт «что считаем» (дерево балансов), Bridge — «как печатаем».
 *   В теме 13 обход такого дерева можно будет вынести в Visitor.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите Portfolio со счетами и вложенным Portfolio;
        //       выведите общий баланс через PlainFormat и CsvFormat
    }
}
