package m76_spring_rest_testing.practice.task05;

/**
 * Задача 05 — Модуль 76: Интеграционный тест @SpringBootTest + TestRestTemplate
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ:
 *   org.springframework.boot:spring-boot-starter-web:3.2.x,
 *   org.springframework.boot:spring-boot-starter-test:3.2.x (см. theory.md).
 *
 * Это ТЕСТ-КЛАСС (без main). Поднимает ВЕСЬ контекст и реальный сервер.
 *
 * ЗАДАНИЕ:
 *   1) Класс TaskApiIT05 пометьте
 *        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 *      — поднимется встроенный Tomcat на случайном порту.
 *   2) Внедрите @Autowired TestRestTemplate rest.
 *   3) Тесты (бьют по настоящему HTTP):
 *        getReturnsTask():
 *          ResponseEntity<TaskDto05> r = rest.getForEntity("/api/tasks/1", TaskDto05.class);
 *          assertEquals(HttpStatus.OK, r.getStatusCode());
 *          assertEquals("Кофе", r.getBody().title());
 *        createReturns201():
 *          ResponseEntity<TaskDto05> r = rest.postForEntity("/api/tasks",
 *                 new CreateDto05("Чай"), TaskDto05.class);
 *          assertEquals(HttpStatus.CREATED, r.getStatusCode());
 *
 * ЦЕЛЬ: сквозная проверка через реальный HTTP-стек (без моков).
 *
 * ПОДСКАЗКА: TestRestTemplate сам сериализует тело и десериализует ответ.
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationApp05 {}
