/**
 * Задача 04 — Тема 16: свой IoC-контейнер
 *
 * ЗАДАНИЕ:
 *   Напишите минимальный контейнер, который создаёт объекты и связывает их:
 *     - Container (файл Container.java): хранит Map<Class<?>, Supplier<?>>;
 *       <T> void register(Class<T> type, Supplier<T> recipe);
 *       <T> T resolve(Class<T> type) — вызывает рецепт;
 *     - Repo (файл Repo.java): String find() → "данные"; InMemoryRepo реализует;
 *     - Service (файл Service.java): зависит от Repo (конструктор); run() →
 *       "обработано: " + repo.find().
 *   В main: зарегистрируйте Repo и Service (рецепт Service берёт Repo через
 *   resolve), затем resolve(Service.class) и вызовите run().
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   обработано: данные
 *
 * ТРЕБОВАНИЯ:
 *   - контейнер сам собирает Service, внедряя в него Repo;
 *   - в main нет ручного new Service(new InMemoryRepo()) — это делает контейнер
 *     через зарегистрированные рецепты;
 *   - resolve возвращает готовый, связанный объект.
 *
 * ПОДСКАЗКА:
 *   register(Service.class, () -> new Service(container.resolve(Repo.class)));
 *   Именно это (только автоматически, по аннотациям) делает Spring.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: создайте Container, register(Repo) и register(Service), resolve(Service).run()
    }
}
