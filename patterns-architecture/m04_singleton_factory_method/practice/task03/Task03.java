package m04_singleton_factory_method.practice.task03;

/**
 * Задача 03 — Тема 04: Singleton — holder idiom (ленивый + потокобезопасный)
 *
 * ЗАДАНИЕ:
 *   Реализуйте ExpensiveRegistry (файл ExpensiveRegistry.java) как ленивый
 *   потокобезопасный Singleton через идиому «inner holder»:
 *     - приватный конструктор, который печатает "Registry создан" (чтобы
 *       видеть момент создания);
 *     - вложенный приватный статический класс Holder с полем
 *       static final ExpensiveRegistry INSTANCE = new ExpensiveRegistry();
 *     - getInstance() возвращает Holder.INSTANCE;
 *     - метод register(String key) / boolean contains(String key) — хранит ключи.
 *   В main: сначала выведите строку «до первого getInstance», затем вызовите
 *   getInstance() — конструктор должен сработать ИМЕННО здесь (лениво).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   до первого getInstance
 *   Registry создан
 *   contains(A): true
 *
 * ТРЕБОВАНИЯ:
 *   - экземпляр создаётся при ПЕРВОМ обращении к getInstance(), не раньше;
 *   - потокобезопасность обеспечивается загрузкой вложенного класса (без
 *     synchronized и без double-checked locking);
 *   - конструктор приватный.
 *
 * ПОДСКАЗКА:
 *   JVM гарантирует, что класс Holder загрузится (и создаст INSTANCE) лениво и
 *   потокобезопасно — только при первом обращении к Holder.INSTANCE.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: выведите строку до getInstance, затем получите экземпляр,
        //       зарегистрируйте ключ и проверьте contains
    }
}
