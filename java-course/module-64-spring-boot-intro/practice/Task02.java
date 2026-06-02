/**
 * Задача 02 — Модуль 64: CommandLineRunner
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework.boot:spring-boot-starter:3.2.x (см. theory.md).
 *
 * ЗАДАНИЕ:
 *   1) StartupRunner02 уже реализует CommandLineRunner — допишите метод run():
 *      выведите "Приложение запущено!" и количество переданных аргументов (args.length).
 *   2) Пометьте StartupRunner02 как @Component, чтобы Spring сам его нашёл и вызвал.
 *   3) Запустите приложение (можно с аргументами: --name=Иван foo bar) и проверьте вывод.
 *
 * ПРИМЕР ВЫВОДА (запуск с аргументами "foo bar"):
 *   Приложение запущено!
 *   Аргументов: 2
 *
 * ПОДСКАЗКА:
 *   CommandLineRunner вызывается автоматически ПОСЛЕ поднятия контекста.
 *   Метод: public void run(String... args)
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Task02 {
    public static void main(String[] args) {
        SpringApplication.run(Task02.class, args);
    }
}

// TODO: добавьте @Component
class StartupRunner02 implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // TODO: выведите "Приложение запущено!"
        // TODO: выведите "Аргументов: " + args.length
    }
}
