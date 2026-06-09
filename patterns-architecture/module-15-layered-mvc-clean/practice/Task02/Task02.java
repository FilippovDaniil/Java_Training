/**
 * Задача 02 — Тема 15: DTO vs доменная модель между слоями
 *
 * ЗАДАНИЕ:
 *   Не отдавайте доменный объект наружу как есть — у него могут быть «лишние»/
 *   чувствительные поля. Введите DTO для Presentation:
 *     - Client (файл Client.java) — домен: id, name, internalNote (внутренняя
 *       заметка, наружу не отдаётся);
 *     - ClientDto (файл ClientDto.java) — данные для отображения: id, name (без note);
 *     - ClientService (файл ClientService.java): toDto(Client) → ClientDto
 *       (маппинг, без internalNote).
 *   В main создайте доменного Client с заметкой, получите DTO и выведите его —
 *   internalNote НЕ должно попасть наружу.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   DTO: C-1 / Аня
 *   (internalNote наружу не отдан)
 *
 * ТРЕБОВАНИЯ:
 *   - ClientDto не содержит internalNote;
 *   - маппинг domain → DTO выполняет сервис (граница слоёв);
 *   - наружу (в Presentation) уходит DTO, а не доменный Client.
 *
 * ПОДСКАЗКА:
 *   Разделение domain/DTO защищает от утечки внутренних полей и развязывает
 *   формат API от внутренней модели.
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: создайте Client с internalNote, получите ClientDto через сервис, выведите DTO
    }
}
