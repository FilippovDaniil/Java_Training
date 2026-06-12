package m62_spring_core_configuration.practice.task02;

/**
 * Задача 02 — Модуль 62: Environment — программный доступ к свойствам
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Вместо @Value используйте интерфейс Environment для чтения свойств:
 *   1) Внедрите Environment в компонент ConfigReader через @Autowired (или конструктор).
 *   2) Реализуйте метод printConfig():
 *        - прочитайте "app.name" через env.getProperty("app.name")
 *        - прочитайте "app.port" как Integer: env.getProperty("app.port", Integer.class, 9090)
 *        - прочитайте "app.debug" с дефолтом "false"
 *        - выведите активные профили: env.getActiveProfiles()
 *   3) В main() создайте контекст, получите ConfigReader и вызовите printConfig().
 *
 * СРАВНЕНИЕ:
 *   | Способ                      | Когда удобен                                 |
 *   |-----------------------------|----------------------------------------------|
 *   | @Value("${key}")            | простое внедрение в конкретное поле          |
 *   | Environment.getProperty()   | динамические ключи, проверка наличия, циклы  |
 *   | @ConfigurationProperties    | группа свойств с префиксом (модуль 65)       |
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   Имя: MyService
 *   Порт: 8080
 *   Debug: false
 *   Активные профили: [] (пусто, если профиль не задан)
 *
 * ПОДСКАЗКА:
 *   String name = env.getProperty("app.name");
 *   int port = env.getProperty("app.port", Integer.class, 9090);
 *
 * ПРИМЕР app.properties (тот же файл из Task01):
 *   app.name=MyService
 *   app.port=8080
 *   app.version=1.0.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public class Task02 {

    public static void main(String[] args) {
        // TODO: создайте контекст с AppConfig2.class
        //       получите бин ConfigReader и вызовите printConfig()
    }
}
