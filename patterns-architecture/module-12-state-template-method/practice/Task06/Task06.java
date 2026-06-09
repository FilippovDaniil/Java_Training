/**
 * Задача 06 — Тема 12: State + Template Method вместе
 *
 * ЗАДАНИЕ:
 *   Шаблонный метод задаёт каркас «шага» обработки, а смена состояния workflow —
 *   это State.
 *     - State: WorkflowState (файл WorkflowState.java): WorkflowState advance();
 *       String label(); StartState (advance → End, label "START") и EndState
 *       (advance → остаётся, label "END");
 *     - Workflow (файл Workflow.java) — контекст: advance() меняет состояние,
 *       label() возвращает текущую метку;
 *     - Template Method: абстрактный WorkflowRunner (файл WorkflowRunner.java)
 *       с final String runStep(Workflow w):
 *         вернуть before() + "[" + (advance, затем w.label()) + "]" + after();
 *       abstract String before(); abstract String after();
 *     - LoggingRunner (before ">> ", after " <<").
 *   В main: создайте Workflow (START), прогоните runStep через LoggingRunner.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   >> [END] <<
 *
 * ТРЕБОВАНИЯ:
 *   - State: переход START → END живёт в состояниях, не в контексте;
 *   - Template Method: runStep() — final, before()/after() задают подклассы;
 *   - в runStep шаг смены состояния (advance) общий, обрамление — переопределяемое.
 *
 * ПОДСКАЗКА:
 *   runStep сначала зовёт before(), затем w.advance() (State), берёт w.label(),
 *   затем after(). Каркас один, обрамление меняют подклассы.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: Workflow w (START); LoggingRunner; выведите runStep(w)
    }
}
