/**
 * Задача 03 — Модуль 61: области видимости бинов — singleton vs prototype
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Сравните поведение singleton и prototype бинов при двукратном
 *   обращении к контексту.
 *
 *     1) Создайте класс SingletonNotifier с @Component и @Scope("singleton").
 *     2) Создайте класс PrototypeNotifier с @Component и @Scope("prototype").
 *     3) В main дважды вызовите context.getBean() для каждого класса
 *        и сравните ссылки через ==.
 *     4) Объясните результат в комментарии.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Singleton одинаковые? true
 *   Prototype одинаковые? false
 *
 * ПОДСКАЗКА:
 *   @Scope("singleton") — умолчание, создаётся один раз при старте контекста.
 *   @Scope("prototype") — новый объект при каждом context.getBean().
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class Task03 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig03.class);

        // --- Singleton ---
        // TODO: получить бин SingletonNotifier дважды
        // TODO: сравнить ссылки (==) и вывести результат
        // Ожидается: true

        // --- Prototype ---
        // TODO: получить бин PrototypeNotifier дважды
        // TODO: сравнить ссылки (==) и вывести результат
        // Ожидается: false

        context.close();
    }
}

@Configuration
@ComponentScan
class AppConfig03 {}

// ============================================================
// Singleton: один экземпляр на весь контекст
// ============================================================

// TODO: добавьте @Component
// TODO: добавьте @Scope("singleton")   (можно не указывать — это умолчание)
class SingletonNotifier {
    public void send(String message) {
        System.out.println("[SINGLETON:" + System.identityHashCode(this) + "] " + message);
    }
}

// ============================================================
// Prototype: новый экземпляр при каждом запросе
// ============================================================

// TODO: добавьте @Component
// TODO: добавьте @Scope("prototype")
class PrototypeNotifier {
    public void send(String message) {
        System.out.println("[PROTOTYPE:" + System.identityHashCode(this) + "] " + message);
    }
}
