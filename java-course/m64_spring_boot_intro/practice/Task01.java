package m64_spring_boot_intro.practice;

/**
 * Задача 01 — Модуль 64: Минимальное Spring Boot приложение
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 * Bare-javac НЕ компилируется без зависимостей — запускайте в IDE/Gradle.
 *
 * ЗАДАНИЕ:
 *   1) Над классом Task01 добавьте аннотацию @SpringBootApplication.
 *   2) В методе main вызовите SpringApplication.run(Task01.class, args).
 *   3) Запустите программу. В консоли должны появиться баннер Spring Boot
 *      и строка вида "Started Task01 in ... seconds".
 *
 * ВОПРОС ДЛЯ РАЗМЫШЛЕНИЯ (ответьте комментарием в коде):
 *   - Из каких трёх аннотаций состоит @SpringBootApplication?
 *   - Почему без spring-boot-starter-web приложение стартует и сразу завершается?
 *
 * ПОДСКАЗКА:
 *   @SpringBootApplication
 *   public class Task01 {
 *       public static void main(String[] args) {
 *           SpringApplication.run(Task01.class, args);
 *       }
 *   }
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: добавьте @SpringBootApplication
public class Task01 {

    public static void main(String[] args) {
        // TODO: SpringApplication.run(Task01.class, args);
    }
}
