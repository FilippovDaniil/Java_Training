package m14_grasp.practice.task06;

/**
 * Задача 06 — Тема 14: Pure Fabrication (искусственный класс-помощник)
 *
 * ЗАДАНИЕ:
 *   Обязанность «хранить клиентов» не принадлежит самому клиенту (это повредило бы
 *   его связности). Вынесите её в искусственный класс:
 *     - Customer (файл Customer.java) — доменные данные: id, name (без логики хранения);
 *     - CustomerRepository (файл CustomerRepository.java) — Pure Fabrication:
 *       хранилище на Map; save(Customer), findById(String), count().
 *   В main создайте пару клиентов, сохраните в репозиторий, найдите одного по id,
 *   выведите количество.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Найден: Аня
 *   Всего клиентов: 2
 *
 * ТРЕБОВАНИЯ:
 *   - Customer НЕ умеет сохранять/искать себя (хранение — не его дело);
 *   - CustomerRepository — искусственный класс (его нет в предметной области),
 *     держащий техническую обязанность хранения;
 *   - так сохраняются High Cohesion (Customer чист) и Low Coupling.
 *
 * ПОДСКАЗКА:
 *   Репозитории, мапперы, сервисы — типичные Pure Fabrication: их «придумывают»,
 *   чтобы не сваливать техническую работу в доменные объекты.
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: создайте Customer-ов, save в CustomerRepository, findById, count
    }
}
