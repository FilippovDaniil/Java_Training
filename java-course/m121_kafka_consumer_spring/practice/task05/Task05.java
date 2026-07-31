package m121_kafka_consumer_spring.practice.task05;

/**
 * Задача 05 — Модуль 121: concurrency, распределение партиций, порядок внутри ключа
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер;
 * топик task.created должен иметь 6 партиций (модуль 119, задача 02). Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task05.java (точка входа), ParallelListener05.java (слушатель),
 *               LoadGenerator05.java (генератор событий).
 *
 * ЗАДАНИЕ:
 *   1) В ParallelListener05 поставьте concurrency = "3" и печатайте:
 *        имя потока, партицию, ключ, значение.
 *   2) В LoadGenerator05 отправьте 20 событий: 10 с ключом "task-42", 10 с ключами task-1..task-10.
 *   3) Соберите вывод и ответьте:
 *        - сколько партиций досталось каждому потоку;
 *        - в одном ли потоке обрабатывались все события ключа task-42;
 *        - сохранился ли их порядок;
 *        - что изменится при concurrency = "8" на 6 партициях.
 *   4) Проверьте распределение партиций на брокере:
 *        kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group parallel
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   [org.springframework.kafka.KafkaListenerEndpointContainer#0-1-C-1] p3 key=task-42 ...
 *   [...#0-1-C-1] p3 key=task-42 ...          ← тот же поток, порядок сохранён
 *   [...#0-0-C-1] p0 key=task-3  ...
 *   [...#0-2-C-1] p5 key=task-7  ...
 *
 * ЦЕЛЬ: понять, что параллелизм ограничен числом партиций, а порядок по ключу при этом не страдает.
 *
 * ВАЖНО: concurrency больше числа партиций — простаивающие потоки (ресурсы заняты, пользы нет).
 *   Масштабировать нужно партициями, а затем экземплярами сервиса.
 *
 * ПОДСКАЗКА: имя потока — Thread.currentThread().getName(); в нём виден индекс контейнера,
 *   по которому легко считать, какие партиции ушли какому потоку.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task05 {

    public static void main(String[] args) {
        SpringApplication.run(Task05.class, args);
    }
}
