/**
 * Задача 06 — Модуль 72: Иерархия исключений → разные статусы
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   Заведите иерархию бизнес-исключений и сопоставьте каждому HTTP-статус.
 *   1) Базовое BusinessException06 и наследники:
 *        TaskNotFoundException06   → 404
 *        DuplicateTaskException06  → 409
 *        InvalidStatusException06  → 400
 *   2) В контроллере demo(kind): по значению kind бросьте соответствующее исключение
 *      ("missing" → NotFound, "dup" → Duplicate, "status" → Invalid).
 *   3) В GlobalHandler06 (@RestControllerAdvice) объявите ТРИ обработчика —
 *      по одному на каждый подтип — каждый со своим статусом и телом
 *      Map.of("status", <код>, "error", ex.getMessage()).
 *
 *   Проверьте:
 *      GET /api/demo?kind=missing → 404
 *      GET /api/demo?kind=dup     → 409
 *      GET /api/demo?kind=status  → 400
 *
 * ЦЕЛЬ: показать, как одна иерархия исключений отображается в разные коды.
 *
 * ПОДСКАЗКА: Spring выбирает самый специфичный @ExceptionHandler по типу.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

public class Task06 {
    public static void main(String[] args) {
        SpringApplication.run(Task06.class, args);
    }
}
