package m59_spring_core_intro.practice.task01;

/**
 * Задача 01 — Модуль 59: Ручной DI без Spring (проблема жёсткой связанности)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: нет (чистая Java). Эта задача намеренно решается без Spring —
 * чтобы ощутить проблему, которую Spring затем устраняет.
 *
 * ЗАДАНИЕ:
 *   1) Создайте объект InMemoryProductRepository вручную (через new).
 *   2) Передайте его в конструктор ProductService (конструкторный DI вручную).
 *   3) Вызовите productService.listAll() и выведите результат.
 *   4) Теперь представьте: вам нужно заменить InMemoryProductRepository на
 *      DatabaseProductRepository. Найдите, сколько мест в коде придётся изменить.
 *      Запишите ответ в комментарий.
 *
 * ПОДСКАЗКА:
 *   ProductRepository repo = new InMemoryProductRepository();
 *   ProductService svc = new ProductService(repo);
 *   svc.listAll();
 *
 * ЦЕЛЬ:
 *   Понять минусы ручного DI:
 *   - main() (или любой «сборочный» класс) знает о конкретных реализациях
 *   - замена реализации требует правки кода во всех точках создания объектов
 *   - при росте проекта «граф создания» становится неуправляемым
 */

public class Task01 {

    public static void main(String[] args) {
        // TODO 1: создайте InMemoryProductRepository через new
        InMemoryProductRepository memoryProductRepository = new InMemoryProductRepository();
        // TODO 2: создайте ProductService, передав репозиторий в конструктор
        ProductRepository repository = new InMemoryProductRepository();
        ProductService productService = new ProductService(repository);
        // TODO 3: вызовите svc.listAll() и выведите список
        productService.listAll();
        // TODO 4: в комментарии ответьте — сколько мест менять при смене реализации?
    }
}
