/**
 * Задача 04 — Модуль 68: Вложенные ресурсы (≤ 2 уровней)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Спроектируйте подресурсы для Task Tracker, соблюдая правило "не глубже 2 уровней":
 *     - GET  /api/projects/{projectId}/tasks
 *           → "Задачи проекта <projectId>"
 *     - POST /api/projects/{projectId}/tasks
 *           → "Создана задача в проекте <projectId>"
 *     - GET  /api/tasks/{taskId}/comments
 *           → "Комментарии задачи <taskId>"
 *
 *   ВАЖНО: комментарий комментария или автор комментария НЕ должны быть
 *   /api/tasks/1/comments/2/replies/3/author — это слишком глубоко.
 *   Глубокие связи адресуют по собственному id: /api/comments/{id}.
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему глубокая вложенность URI — плохо? (подсказка: хрупкость и длина URL)
 *
 * ПОДСКАЗКА: два @PathVariable в одном пути — это нормально.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
