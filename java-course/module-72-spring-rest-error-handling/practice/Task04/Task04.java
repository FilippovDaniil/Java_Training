/**
 * Задача 04 — Модуль 72: ProblemDetail (RFC 7807)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Верните ошибку в стандартном формате ProblemDetail.
 *   1) get(id): при id <= 0 бросьте TaskNotFoundException04(id) (хранит taskId).
 *   2) В GlobalHandler04 (@RestControllerAdvice) обработчик возвращает ProblemDetail:
 *        - ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage())
 *        - pd.setTitle("Задача не найдена")
 *        - pd.setProperty("taskId", ex.getTaskId())
 *        - вернуть pd (статус возьмётся из самого ProblemDetail!)
 *
 * ОЖИДАЕМЫЙ JSON:
 *   { "type":"about:blank", "title":"Задача не найдена", "status":404,
 *     "detail":"Задача 0 не найдена", "taskId":0 }
 *
 * ВОПРОС (ответьте комментарием):
 *   Почему ProblemDetail не нужно оборачивать в ResponseEntity?
 *
 * ПОДСКАЗКА: import org.springframework.http.ProblemDetail;
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
