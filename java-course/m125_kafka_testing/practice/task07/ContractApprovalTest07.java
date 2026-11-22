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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ContractApprovalTest07 {

    private final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules(); // JSR-310 (Instant)

    @Test
    void event_json_matches_golden_contract() throws Exception {
        TaskCreated event = new TaskCreated(
                42, "Отчёт", 10,
                Instant.parse("2026-07-31T10:15:30Z"));

        String actual = mapper.writeValueAsString(event);

        String golden = """
                {
                  "id": 42,
                  "title": "Отчёт",
                  "authorId": 10,
                  "occurredAt": "2026-07-31T10:15:30Z",
                  "priority": "NORMAL"
                }
                """;

        // strict=false: порядок полей не важен, значения — важны
        JSONAssert.assertEquals(golden, actual, false);
    }

    @Test
    void unknown_fields_are_ignored_by_consumer() throws Exception {
        // лишнее поле "extra" — forward-совместимость
        String json = """
                {"id":42,"title":"Отчёт","authorId":10,
                 "occurredAt":"2026-07-31T10:15:30Z","priority":"NORMAL",
                 "extra":"новое поле из будущего"}
                """;

        // консьюмер настроен с FAIL_ON_UNKNOWN_PROPERTIES=false (Spring Boot default)
        TaskCreated parsed = mapper.readValue(json, TaskCreated.class);
        assertThat(parsed.id()).isEqualTo(42);
        assertThat(parsed.title()).isEqualTo("Отчёт");
    }

    @Test
    void missing_optional_field_gets_default() throws Exception {
        // JSON без priority -> дефолт "NORMAL" (через компактный конструктор)
        String json = """
                {"id":42,"title":"Отчёт","authorId":10,
                 "occurredAt":"2026-07-31T10:15:30Z"}
                """;

        TaskCreated parsed = mapper.readValue(json, TaskCreated.class);
        assertThat(parsed.priority()).isEqualTo("NORMAL");
    }
}
