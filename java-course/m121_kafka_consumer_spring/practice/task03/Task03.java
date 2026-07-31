package m121_kafka_consumer_spring.practice.task03;

/**
 * Задача 03 — Модуль 121: JSON-десериализация события (JsonDeserializer, trusted packages)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main). Публикует события задача 07 модуля 120 (или CLI-продюсер вручную).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task03.java (точка входа), TaskCreated03.java (событие),
 *               NotificationListener03.java (слушатель).
 *
 * ЗАДАНИЕ:
 *   1) Допишите TaskCreated03 (id, title, authorId, occurredAt) — контракт должен совпадать
 *      с событием продюсера из модуля 120.
 *   2) Настройте десериализацию:
 *        spring.kafka.consumer.value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
 *        spring.kafka.consumer.properties.spring.json.trusted.packages: "m121_kafka_consumer_spring.practice.task03"
 *        spring.kafka.consumer.properties.spring.json.value.default.type: <полное имя TaskCreated03>
 *   3) В NotificationListener03 реализуйте onTaskCreated(TaskCreated03 event): печатать
 *      «уведомление автору {authorId}: создана задача {id} — {title}».
 *   4) Проверьте, что при опечатке в trusted.packages появляется ошибка десериализации —
 *      и почему эта защита существует.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   уведомление автору 10: создана задача 1 — Написать отчёт
 *
 * ЦЕЛЬ: получать типизированное событие вместо строки и понимать роль настроек JsonDeserializer.
 *
 * ВАЖНО: trusted.packages защищает от десериализации произвольных классов из заголовка
 *   __TypeId__ (риск исполнения чужого типа). Значение "*" отключает защиту — так на проде не делают.
 *
 * ПОДСКАЗКА: если продюсер отправлял с add.type.headers=false, тип нужно задать самому —
 *   через spring.json.value.default.type или через тип параметра метода + JsonMessageConverter.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task03 {

    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
