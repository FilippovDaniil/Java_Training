package m66_spring_boot_devops.practice.task01;

/**
 * Задача 01 — Модуль 66: Логирование через SLF4J
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 *   SLF4J + Logback входят в стартер автоматически.
 *
 * ЗАДАНИЕ:
 *   1) В PaymentService01 объявите логгер:
 *        private static final Logger log = LoggerFactory.getLogger(PaymentService01.class);
 *   2) В методе pay(orderId, amount):
 *        - log.info("Оплата заказа {} на сумму {}", orderId, amount);
 *        - если amount <= 0: log.warn("Некорректная сумма: {}", amount);
 *        - log.debug("Детали транзакции для заказа {}", orderId);
 *   3) В application.properties задайте:
 *        logging.level.root=INFO
 *        (попробуйте также DEBUG для пакета сервиса и сравните вывод)
 *   4) Вызовите pay(...) из CommandLineRunner и посмотрите консоль.
 *
 * ВОПРОС: почему log.debug("x = {}", x) лучше, чем log.debug("x = " + x)?
 *   (ответьте комментарием)
 *
 * ПОДСКАЗКА:
 *   {} — плейсхолдер SLF4J; подстановка выполняется лениво, только если уровень включён.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task01 {
    public static void main(String[] args) {
        SpringApplication.run(Task01.class, args);
    }
}
