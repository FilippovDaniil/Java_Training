package m124_kafka_event_patterns.practice.task04;

/**
 * Задача 04 — Модуль 124: CQRS-проекция — модель чтения из потока событий
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter-web, spring-boot-starter-data-jpa, h2,
 *   spring-kafka, jackson-databind. Нужен брокер. Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task04.java (точка входа), TaskView04.java (таблица чтения),
 *               TaskViewRepository04.java, TaskProjector04.java (слушатель-проектор),
 *               DashboardController04.java (запросы к проекции).
 *
 * ЗАДАНИЕ:
 *   1) TaskView04 — денормализованная модель чтения: taskId (PK), title, authorId, status,
 *      lastEventVersion (long), updatedAt.
 *   2) TaskProjector04 — слушатель "task.created" и "task.status.changed" (группа "projector"):
 *        - upsert по taskId (найти или создать, обновить поля);
 *        - применять событие ТОЛЬКО если его версия/время новее сохранённого (защита от
 *          повторов и от событий, пришедших не по порядку);
 *        - ручной ack после успешного сохранения.
 *   3) DashboardController04:
 *        - GET /api/dashboard/tasks?status=DONE — список из проекции;
 *        - GET /api/dashboard/stats — количество задач по статусам (GROUP BY).
 *   4) Эксперимент A: отправьте одно и то же событие дважды — проекция не должна «удвоиться».
 *   5) Эксперимент B: сбросьте офсеты группы projector в начало (kafka-consumer-groups.sh
 *      --reset-offsets --to-earliest) и очистите таблицу — проекция должна полностью
 *      пересобраться из топика.
 *
 * ОЖИДАЕМЫЙ ИТОГ: запросы «покажи все задачи в статусе DONE» обслуживаются локальной таблицей,
 *   которую можно в любой момент выбросить и пересобрать из лога событий.
 *
 * ЦЕЛЬ: понять модель чтения как производную от потока событий, а не как «вторую копию правды».
 *
 * ВАЖНО: проекция — eventually consistent. Между REST-ответом на запись и появлением данных
 *   в проекции проходит время (обычно десятки миллисекунд). UI должен это учитывать: показывать
 *   собственное локальное состояние либо явно отражать «обновляется».
 *
 * ПОДСКАЗКА: возможность пересборки — главное требование к проекции. Оно достигается тремя
 *   условиями: идемпотентный upsert, достаточный retention (или compaction) в топике и
 *   отсутствие в проекторе логики, зависящей от «сейчас» (Instant.now() в вычислениях).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
