/**
 * Задача 05 — Модуль 60: Сеттерная инъекция (setter injection) и опциональные зависимости
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   PostService должен уметь работать со спам-фильтром (SpamFilter),
 *   но спам-фильтр — опциональный компонент (может не быть в контексте).
 *
 *     1) Добавьте в PostService05 поле SpamFilter spamFilter (НЕ final — опционально).
 *     2) Создайте сеттер setSpamFilter(SpamFilter spamFilter) и поставьте на него
 *        @Autowired(required = false).
 *        Spring вызовет сеттер только если бин SpamFilter присутствует в контексте.
 *     3) В методе publish(String text) проверьте: если spamFilter != null,
 *        вызовите spamFilter.check(text) и, если вернёт false, выбросьте
 *        IllegalArgumentException("Спам обнаружен").
 *        Иначе — выведите "Опубликовано: " + text.
 *     4) В main запустите два сценария:
 *        Сценарий A: контекст WithFilter (SpamFilter зарегистрирован) → publish("Купи диплом!")
 *        Сценарий B: контекст NoFilter (SpamFilter НЕ зарегистрирован) → publish("Интересная статья")
 *
 * ПОДСКАЗКА:
 *   @Autowired(required = false)
 *   public void setSpamFilter(SpamFilter spamFilter) {
 *       this.spamFilter = spamFilter;
 *   }
 *
 *   Сеттерная инъекция уместна именно для опциональных зависимостей.
 *   Для обязательных — используйте конструктор (см. Task04).
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Task05 {

    public static void main(String[] args) {
        // Сценарий A: с фильтром
        System.out.println("--- Сценарий A: SpamFilter присутствует ---");
        // TODO: создайте контекст с WithFilterConfig.class
        // TODO: получите PostService05, вызовите publish("Купи диплом!")
        // TODO: обработайте исключение и выведите сообщение об ошибке
        // TODO: закройте контекст

        // Сценарий B: без фильтра
        System.out.println("--- Сценарий B: SpamFilter отсутствует ---");
        // TODO: создайте контекст с NoFilterConfig.class
        // TODO: получите PostService05, вызовите publish("Интересная статья")
        // TODO: закройте контекст
    }
}

// Конфигурация С фильтром (SpamFilter зарегистрирован через @ComponentScan)
// TODO: @Configuration + @ComponentScan, включающий пакет где лежит SpamFilter
class WithFilterConfig { }

// Конфигурация БЕЗ фильтра (пакет для SpamFilter не сканируется)
// TODO: @Configuration + @ComponentScan, не включающий SpamFilter
class NoFilterConfig { }

// ============================================================
// Спам-фильтр — готов
// ============================================================

@Component
class SpamFilter {
    /**
     * Возвращает false, если текст содержит слово "диплом" (антиспам-заглушка).
     */
    public boolean check(String text) {
        return !text.toLowerCase().contains("диплом");
    }
}

// ============================================================
// Сервис — реализуйте сеттерную инъекцию
// ============================================================

@Service
class PostService05 {

    // TODO: объявите поле  private SpamFilter spamFilter  (не final!)

    // TODO: добавьте сеттер с @Autowired(required = false)
    //       public void setSpamFilter(SpamFilter spamFilter) { ... }

    public void publish(String text) {
        // TODO: если spamFilter != null и check(text) == false → IllegalArgumentException
        // TODO: иначе → System.out.println("Опубликовано: " + text)
    }
}
