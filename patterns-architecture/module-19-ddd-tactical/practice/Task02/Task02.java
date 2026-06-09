/**
 * Задача 02 — Тема 19: Repository с «коллекционным» интерфейсом
 *
 * ЗАДАНИЕ:
 *   Репозиторий, ведущий себя как коллекция агрегатов:
 *     - Product (файл Product.java): id, name (агрегат-заглушка);
 *     - ProductRepository (файл ProductRepository.java): void add(Product);
 *       Product findById(String id); List<Product> all();
 *     - InMemoryProductRepository (файл ...) на Map.
 *   В main добавьте 3 товара, выведите количество через all() и найдите один по id.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Всего товаров: 3
 *   Найден: Книга
 *
 * ТРЕБОВАНИЯ:
 *   - интерфейс «как у коллекции» (add/findById/all), скрывает хранилище;
 *   - all() возвращает копию/неизменяемое представление (не внутренний изменяемый список);
 *   - репозиторий оперирует целыми Product.
 *
 * ПОДСКАЗКА:
 *   all() → List.copyOf(store.values()). Это та же идея Repository, но с обзором
 *   всей «коллекции».
 */

public class Task02 {
    public static void main(String[] args) {
        // TODO: добавьте 3 товара, выведите all().size(), найдите по id
    }
}
