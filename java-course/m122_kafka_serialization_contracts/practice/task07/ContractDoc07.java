package m122_kafka_serialization_contracts.practice.task07;

/**
 * Носитель документации контракта: спецификация событий + чек-лист миграции.
 * Запускается как обычный main (println), Spring не нужен.
 *
 * ЗАДАНИЕ: заполните SPEC — это артефакт, который в реальном проекте лежит в репозитории
 *   рядом с кодом (AsyncAPI-файл или ADR) и проходит ревью вместе с изменением схемы.
 *
 * КРИТЕРИИ ПРИЁМКИ:
 *   - перечислены топики, ключи, версии, владелец контракта и подписчики;
 *   - для каждой версии указан набор полей и что изменилось относительно предыдущей;
 *   - есть правило совместимости (какие изменения допустимы без новой версии топика);
 *   - есть чек-лист миграции с порядком выпуска и условием выключения двойной публикации.
 */
public class ContractDoc07 {
    public static void main(String[] args) {
        String spec = """
                # TODO: спецификация контракта событий Task Tracker
                #
                # asyncapi: 3.0.0
                # info: { title: Task Tracker Events, version: "2.0.0" }
                # channels:
                #   task.created:
                #     description: события создания задачи (v1 и v2, совместимы)
                #     key: taskId (string)
                #     headers: eventId, eventType, version, traceId
                #     messages:
                #       TaskCreatedV1: { taskId: long, title: string, authorId: long }
                #       TaskCreatedV2: { ...v1, priority: string = NORMAL, deadline: timestamp|null }
                #   task.created.v2:
                #     description: ломающая версия (taskId: string) — только для переехавших подписчиков
                #
                # ВЛАДЕЛЕЦ КОНТРАКТА: ...
                # ПОДПИСЧИКИ: notifier, analytics, search-indexer
                #
                # ПРАВИЛО СОВМЕСТИМОСТИ: BACKWARD — добавляем только поля с дефолтом;
                #   переименование/смена типа -> новый топик .vN
                #
                # ЧЕК-ЛИСТ МИГРАЦИИ v1 -> v2 (заполните):
                # [ ] схема v2 проверена на совместимость (правила из задачи 06)
                # [ ] потребители обновлены до кода, понимающего v2
                # [ ] продюсер публикует v2 (совместимо) / включена двойная публикация (ломающе)
                # [ ] подписчики переехали — подтверждено метриками (lag по группам = 0)
                # [ ] двойная публикация выключена (DUAL_PUBLISH = false)
                # [ ] старый топик дожил retention и удалён
                """;
        System.out.println(spec);
    }
}
