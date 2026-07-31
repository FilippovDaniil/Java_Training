package m121_kafka_consumer_spring.practice.task04;

/**
 * Задача 04 — Модуль 121: ручной коммит офсета (ack-mode MANUAL_IMMEDIATE) и повторная доставка
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: spring-boot-starter, spring-kafka. Нужен брокер.
 * Запуск в IDE (▶ main).
 *
 * ФАЙЛЫ ЗАДАЧИ: Task04.java (точка входа), ManualAckListener04.java (слушатель с Acknowledgment).
 *
 * ЗАДАНИЕ:
 *   1) Включите ручной коммит:
 *        spring.kafka.consumer.enable-auto-commit: false
 *        spring.kafka.listener.ack-mode: manual_immediate
 *   2) В ManualAckListener04 реализуйте onTaskCreated(value, ack):
 *        - напечатать запись;
 *        - если значение содержит "boom" — БРОСИТЬ RuntimeException до ack.acknowledge();
 *        - иначе ack.acknowledge().
 *   3) Эксперимент A: отправьте обычное сообщение — офсет группы вырос
 *        (kafka-consumer-groups.sh --describe --group manual-ack: LAG = 0).
 *   4) Эксперимент B: отправьте сообщение со словом boom — увидите повторные попытки
 *        и LAG > 0: офсет не закоммичен, запись будет читаться снова.
 *   5) Эксперимент C: переставьте ack.acknowledge() ПЕРЕД обработкой и повторите B —
 *        офсет уехал, «сломанное» сообщение потеряно. Запишите вывод в комментарии.
 *
 * ОЖИДАЕМЫЙ ИТОГ: понимание разницы
 *   ack ПОСЛЕ обработки  = at-least-once (возможен повтор, потери нет);
 *   ack ДО обработки     = at-most-once  (потеря при сбое, повторов нет).
 *
 * ЦЕЛЬ: научиться управлять офсетом руками и осознанно выбирать гарантию доставки.
 *
 * ВАЖНО: при повторной доставке ваш обработчик должен быть идемпотентным — иначе клиент
 *   получит два письма об одном событии (модуль 123).
 *
 * ПОДСКАЗКА: MANUAL_IMMEDIATE коммитит сразу при вызове acknowledge(); MANUAL — складывает
 *   подтверждения и коммитит по завершении обработки партии poll'а.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {

    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
