package m67_spring_rest_http_backend.practice.task03;

/**
 * Задача 03 — Модуль 67: Идемпотентность на практике
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   В контроллере есть счётчик созданных задач (createdCount) и состояние статуса (status).
 *   1) addViaPost(): POST /api/counter — УВЕЛИЧИВАЕТ createdCount на 1 (НЕ идемпотентно).
 *      Верните текущее значение createdCount.
 *   2) setViaPut(): PUT /api/status?value=DONE — УСТАНАВЛИВАЕТ status = value (идемпотентно).
 *      Верните текущий status.
 *   3) Запустите и проверьте вручную (curl/Postman):
 *        - вызвать POST трижды → createdCount = 3 (каждый вызов меняет состояние)
 *        - вызвать PUT ?value=DONE трижды → status = "DONE" (результат один и тот же)
 *
 * ВЫВОД (ответьте комментарием):
 *   Почему повторный POST опасен при сетевых ретраях, а повторный PUT — безопасен?
 *
 * ПОДСКАЗКА:
 *   @RequestParam String value — читает ?value=...
 *   Для счётчика используйте поле int / AtomicInteger.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Task03 {
    public static void main(String[] args) {
        SpringApplication.run(Task03.class, args);
    }
}
