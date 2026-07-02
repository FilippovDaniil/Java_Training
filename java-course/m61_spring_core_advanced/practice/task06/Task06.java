package m61_spring_core_advanced.practice.task06;

/**
 * Задача 06 — Модуль 61: циклическая зависимость и способы её устранения
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ (часть A — воспроизвести ошибку):
 *   Два бина с конструкторной инъекцией образуют цикл:
 *     NotificationDispatcher(RoutingService rs) — зависит от RoutingService
 *     RoutingService(NotificationDispatcher nd) — зависит от NotificationDispatcher
 *
 *     1) Раскомментируйте блок "ЧАСТЬ A" и запустите — увидите
 *        BeanCurrentlyInCreationException (ожидаемо).
 *     2) Снова закомментируйте его.
 *
 * ЗАДАНИЕ (часть B — исправить через @Lazy):
 *     3) В блоке "ЧАСТЬ B" на одном из конструкторов добавьте @Lazy
 *        к параметру — Spring создаст прокси и разорвёт цикл.
 *     4) Запустите — контекст поднимается, send() выводит сообщение.
 *
 * ПОЯСНЕНИЕ (читайте внимательно):
 *   @Lazy на параметре конструктора создаёт CGLIB-прокси вместо реального объекта.
 *   Цикл разрывается: A получает прокси B, B создаётся полностью, прокси «оживает».
 *   Альтернатива: вынести общую логику в третий бин (предпочтительный рефакторинг).
 *
 * ПОДСКАЗКА:
 *   // Разрываем цикл: NotificationDispatcher получает прокси RoutingService
 *   NotificationDispatcher(@Lazy RoutingService rs) { ... }
 */

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

public class Task06 {

    public static void main(String[] args) {

        // --------------------------------------------------------
        // ЧАСТЬ A: раскомментируйте, запустите, получите ошибку,
        //          снова закомментируйте.
        // --------------------------------------------------------

        System.out.println("=== ЧАСТЬ A: цикл через конструктор (ожидается ошибка) ===");
        try {
            new AnnotationConfigApplicationContext(AppConfigCycleA.class).close();
        } catch (Exception e) {
            System.out.println("Ошибка (ожидаемо): " + e.getClass().getSimpleName());
        }


        // --------------------------------------------------------
        // ЧАСТЬ B: @Lazy разрывает цикл
        // --------------------------------------------------------
        System.out.println("=== ЧАСТЬ B: цикл устранён через @Lazy ===");
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigCycleB.class);){
            // TODO: получить NotificationDispatcherFixed, вызвать dispatch("Привет")
            ctx.getBean(NotificationDispatcherFixed.class).dispatch("Привет");

        }catch(UnsatisfiedDependencyException e){
            System.out.println(e.getMessage());
        }

    }
}
