package m73_spring_rest_collections.practice.task07;

/**
 * Задача 07 — Модуль 73: МИНИ-ПРОЕКТ «Коллекция Task Tracker: фильтр + сортировка + пагинация»
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x (см. theory.md).
 *
 * ЦЕЛЬ: собрать «настоящий» эндпоинт списка задач, объединив всё из модуля 73:
 *       фильтрацию по параметрам → сортировку → пагинацию → ответ с метаданными.
 *
 * ЗАДАНИЕ:
 *   search(): GET /api/tasks?status=&assignee=&sortBy=&dir=&page=&size=
 *     ПОРЯДОК ОБРАБОТКИ (важен!):
 *       1) ФИЛЬТР: применить необязательные status, assignee (накопление условий на Stream).
 *       2) СОРТИРОВКА: sortBy ∈ {"title","priority"} (белый список!), dir ∈ {"asc","desc"};
 *          неизвестное поле → IllegalArgumentException.
 *       3) ПАГИНАЦИЯ: по page/size нарезать срез (безопасно, с Math.min).
 *       4) ОТВЕТ: вернуть PagedResponse07 c content, page, size, totalElements (число ПОСЛЕ
 *          фильтра, но ДО пагинации), totalPages, hasNext.
 *
 *   Параметры:
 *     status      @RequestParam(required=false)
 *     assignee    @RequestParam(required=false)
 *     sortBy      @RequestParam(defaultValue="title")
 *     dir         @RequestParam(defaultValue="asc")
 *     page        @RequestParam(defaultValue="0")
 *     size        @RequestParam(defaultValue="5")
 *
 *   Проверьте:
 *     GET /api/tasks?status=NEW&sortBy=priority&dir=desc&page=0&size=2
 *
 * КОНВЕЙЕР:
 *
 *   ALL ──filter(status,assignee)──► ──sort(sortBy,dir)──► ──page(page,size)──► PagedResponse07
 *                                              (totalElements = размер после фильтра)
 *
 * ПОДСКАЗКА: переиспользуйте Task04 (фильтр), Task03 (сортировка), Task05 (метаданные).
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task07 {
    public static void main(String[] args) {
        SpringApplication.run(Task07.class, args);
    }
}
