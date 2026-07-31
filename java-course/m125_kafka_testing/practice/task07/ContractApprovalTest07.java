package m125_kafka_testing.practice.task07;

/**
 * Контрактный (approval) тест события: фиксирует «золотой» JSON.
 *
 * ЗАДАНИЕ:
 *   1) event_json_matches_golden_contract():
 *        - сериализовать событие TaskCreated (id 42, title "Отчёт", authorId 10,
 *          occurredAt фиксированный Instant.parse("2026-07-31T10:15:30Z"));
 *        - сравнить с эталонной строкой JSON, объявленной в тесте (text-блок).
 *   2) unknown_fields_are_ignored_by_consumer(): десериализовать JSON с ЛИШНИМ полем
 *      и убедиться, что потребитель не падает (forward-совместимость, модуль 122).
 *   3) missing_optional_field_gets_default(): JSON без priority -> в объекте "NORMAL".
 *   4) Опишите в комментарии процедуру: что делать, когда этот тест падает
 *      (ответ: не «поправить эталон», а сначала оценить совместимость изменения и порядок
 *      выпуска — модуль 122, задача 06).
 *
 * ЦЕЛЬ: сделать изменение контракта видимым событием в code review, а не сюрпризом для
 *       подписчиков в проде.
 *
 * ВАЖНО: занулите нестабильные поля перед сравнением (время, UUID) — иначе тест будет падать
 *   каждый запуск. Фиксированные значения задавайте явно, а не через Instant.now().
 *
 * ПОДСКАЗКА: удобные инструменты — JSONAssert (модуль 101) или JsonUnit с плейсхолдерами
 *   ${json-unit.any-string} для полей, которые проверять по значению не нужно.
 */

import org.junit.jupiter.api.Test;

class ContractApprovalTest07 {

    @Test
    void event_json_matches_golden_contract() {
        // TODO 1
    }

    @Test
    void unknown_fields_are_ignored_by_consumer() {
        // TODO 2
    }

    @Test
    void missing_optional_field_gets_default() {
        // TODO 3
    }
}
