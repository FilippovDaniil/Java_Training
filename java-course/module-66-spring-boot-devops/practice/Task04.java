/**
 * Задача 04 — Модуль 66: Упаковка и запуск (терминальное задание)
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter-web:3.2.x +
 *   плагин org.springframework.boot в build.gradle (даёт задачи bootJar/bootRun).
 *
 * Это ТЕРМИНАЛЬНОЕ задание — основная работа выполняется командами сборки,
 * а main лишь печатает инструкцию.
 *
 * ЗАДАНИЕ (выполните в терминале проекта):
 *   1) Запуск из исходников:
 *        ./gradlew bootRun
 *      (Windows: .\gradlew.bat bootRun)
 *
 *   2) Сборка исполняемого jar:
 *        ./gradlew bootJar
 *      Найдите артефакт в build/libs/*.jar
 *
 *   3) Запуск собранного jar:
 *        java -jar build/libs/<имя>.jar
 *
 *   4) Переопределение свойств при запуске (без пересборки):
 *        java -jar build/libs/<имя>.jar --server.port=9090
 *        java -jar build/libs/<имя>.jar --spring.profiles.active=prod
 *
 *   5) Откройте http://localhost:9090/actuator/health — убедитесь, что порт сменился.
 *
 * ВОПРОС: чем "fat jar" (bootJar) отличается от обычного jar?
 *   (ответьте комментарием — подсказка в theory.md)
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        System.out.println("Задание 04 — терминальное.");
        System.out.println("Соберите и запустите приложение командами из JavaDoc:");
        System.out.println("  ./gradlew bootJar");
        System.out.println("  java -jar build/libs/<имя>.jar --server.port=9090");
        SpringApplication.run(Task04.class, args);
    }
}
