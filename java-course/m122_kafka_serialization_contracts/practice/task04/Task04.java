package m122_kafka_serialization_contracts.practice.task04;

/**
 * Задача 04 — Модуль 122: несколько типов событий в одном топике (type mapping, маршрутизация)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka, jackson-databind. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task04.java (точка входа), TaskCreated04.java, TaskStatusChanged04.java,
 *               MultiTypeListener04.java (маршрутизация по типу).
 *
 * ЗАДАНИЕ:
 *   Один топик "task.events" несёт два типа событий (так делают, когда порядок событий одной
 *   сущности важен: они обязаны лежать в одной партиции одного топика).
 *   1) Настройте отображение логических имён типов на классы — вместо Java-имён в __TypeId__:
 *        spring.kafka.producer.properties.spring.json.type.mapping: >
 *          created:<полное имя TaskCreated04>,status:<полное имя TaskStatusChanged04>
 *        spring.kafka.consumer.properties.spring.json.type.mapping: <то же значение>
 *   2) Отправьте в "task.events" по одному событию каждого типа с ключом = taskId.
 *   3) В MultiTypeListener04 реализуйте два метода-обработчика (@KafkaHandler) в классе
 *      с @KafkaListener на уровне класса — Spring выберет метод по типу payload.
 *   4) Добавьте @KafkaHandler(isDefault = true) для «неизвестного типа» и проверьте его,
 *      отправив в топик произвольный JSON.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   создана задача 42: Написать отчёт
 *   задача 42: NEW -> DONE
 *   неизвестный тип события: {...}   ← default-обработчик, а не падение слушателя
 *
 * ЦЕЛЬ: научиться вести «поток событий сущности» в одном топике, сохраняя порядок и типизацию.
 *
 * ВАЖНО: логические имена типов ("created", "status") — часть контракта; они не должны зависеть
 *   от имён Java-классов, иначе рефакторинг у продюсера сломает потребителей на других стеках.
 *
 * ПОДСКАЗКА: альтернатива type.mapping — свой заголовок eventType + ручной разбор payload
 *   через ObjectMapper; так делают, когда потребители на разных языках.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
