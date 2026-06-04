/**
 * Задача 06 — Модуль 74: Не-CRUD действие + экспорт в CSV
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) НЕ-CRUD действие через подресурс:
 *        archive(): PATCH /api/tasks/{id} с телом { "status":"ARCHIVED" }
 *        → вернуть "Задача <id> переведена в " + dto.status().
 *        (моделируем действие "архивировать" как смену состояния, а не /tasks/{id}/archive)
 *   2) Экспорт коллекции в CSV-файл:
 *        exportCsv(): GET /api/tasks/export (produces "text/csv")
 *        Соберите CSV из ALL: первая строка "id,title,status", затем строки задач.
 *        Верните ResponseEntity со строкой и заголовком
 *        Content-Disposition: attachment; filename="tasks.csv".
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему "архивировать" лучше как PATCH-смена статуса, чем отдельный endpoint /archive?
 *
 * ПОДСКАЗКА: для CSV используйте StringBuilder; разделитель строк — '\n'.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
