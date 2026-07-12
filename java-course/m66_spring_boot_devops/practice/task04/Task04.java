package m66_spring_boot_devops.practice.task04;

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
 *
 * ОТВЕТ:
 *   Fat jar (Spring Boot) содержит все зависимости внутри себя и встроенный сервер,
 *   что позволяет запускать приложение командой java -jar без дополнительных настроек.
 *   Обычный jar содержит только скомпилированный код приложения и требует, чтобы все
 *   зависимости были в classpath при запуске.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Task04 {
    public static void main(String[] args) {
        System.out.println("Задание 04 — терминальное.");
        System.out.println("Соберите и запустите приложение командами из JavaDoc:");
        System.out.println("  1. ./gradlew bootJar");
        System.out.println("  2. java -jar build/libs/Java_Training-1.0-SNAPSHOT.jar --server.port=9090");
        System.out.println("  3. Откройте http://localhost:9090/actuator/health");
        System.out.println();
        System.out.println("Ответ на вопрос: Fat Jar содержит все зависимости внутри,");
        System.out.println("включая встроенный сервер. Обычный jar - только ваш код.");
        SpringApplication.run(Task04.class, args);
    }
}
