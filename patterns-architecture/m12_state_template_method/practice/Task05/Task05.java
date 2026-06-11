package m12_state_template_method.practice.task05;

/**
 * Задача 05 — Тема 12: State (модерация документа, ветвящиеся переходы)
 *
 * ЗАДАНИЕ:
 *   Документ проходит модерацию с ветвлением (одобрить/отклонить):
 *     - интерфейс DocumentState (файл DocumentState.java): DocumentState submit();
 *       DocumentState approve(); DocumentState reject(); String name();
 *     - DraftState (submit → Moderation; approve/reject → остаётся);
 *       ModerationState (approve → Published; reject → Rejected; submit → остаётся);
 *       PublishedState и RejectedState — терминальные (все действия → остаётся);
 *     - Document (файл Document.java) — контекст: submit/approve/reject делегируют
 *       состоянию, status() → name().
 *   В main: проведите документ Draft → submit → approve (Published); отдельно
 *   покажите путь Draft → submit → reject (Rejected).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   DRAFT
 *   MODERATION
 *   PUBLISHED
 *   ---
 *   DRAFT
 *   MODERATION
 *   REJECTED
 *
 * ТРЕБОВАНИЯ:
 *   - переходы (в т.ч. ветвление approve/reject) заданы в состояниях, не в контексте;
 *   - из терминальных состояний переходов нет;
 *   - контекст без switch — только делегирование.
 *
 * ПОДСКАЗКА:
 *   ModerationState — единственное, где есть развилка: approve()→Published,
 *   reject()→Rejected.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: два сценария — Draft→submit→approve и Draft→submit→reject, печатая статус
    }
}
