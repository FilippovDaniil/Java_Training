package m73_spring_rest_collections.practice.task04;

/**
 * Задача 04 — Модуль 73: Фильтрация по нескольким необязательным параметрам
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   filter(): GET /api/tasks?status=DONE&assignee=ivan&priorityMin=3
 *     Все параметры НЕОБЯЗАТЕЛЬНЫ (@RequestParam(required = false)):
 *       - status      (String)
 *       - assignee    (String)
 *       - priorityMin (Integer)
 *     Примените паттерн «накопление условий» к Stream:
 *       Stream<TaskDto04> s = ALL.stream();
 *       if (status != null)      s = s.filter(t -> t.status().equals(status));
 *       if (assignee != null)    s = s.filter(t -> t.assignee().equals(assignee));
 *       if (priorityMin != null) s = s.filter(t -> t.priority() >= priorityMin);
 *       return s.toList();
 *
 *   Проверьте: без параметров → все; ?status=DONE → только DONE; и т.д.
 *
 * ЦЕЛЬ: каждый фильтр применяется только если параметр передан.
 *
 * ПОДСКАЗКА: priorityMin — Integer (а не int!), чтобы мог быть null.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Stream;

public class Task04 {
    public static void main(String[] args) {
        SpringApplication.run(Task04.class, args);
    }
}
