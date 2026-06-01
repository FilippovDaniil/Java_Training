/**
 * Задача 01 — Модуль 30: Singleton
 *
 * ЗАДАНИЕ:
 *   Реализуйте класс Database как Singleton (единственный экземпляр):
 *     - приватный конструктор;
 *     - статическое поле INSTANCE;
 *     - метод getInstance();
 *     - поле счётчика запросов и метод query(String sql),
 *       увеличивающий счётчик.
 *   В main несколько раз получите экземпляр через getInstance(),
 *   убедитесь, что это ОДИН и тот же объект (==), и что счётчик общий.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Один и тот же объект: true
 *   Запросов выполнено: 3
 *
 * ПОДСКАЗКА:
 *   private Database() {}  private static final Database INSTANCE = new Database();
 *   public static Database getInstance() { return INSTANCE; }
 */
public class Task01 {
    public static void main(String[] args) {
        // Получите экземпляр несколько раз, сравните через ==, выполните query
    }
}

class Database {
    // TODO: реализуйте Singleton (приватный конструктор, INSTANCE, getInstance, query)
}
