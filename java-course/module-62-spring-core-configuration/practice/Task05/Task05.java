/**
 * Задача 05 — Модуль 62: несколько @PropertySource и приоритет
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Разберитесь, какой файл свойств "побеждает" при конфликте ключей:
 *   1) Создайте два файла ресурсов:
 *        default.properties   — содержит app.name=DefaultApp, app.env=default, app.debug=false
 *        override.properties  — содержит app.name=OverriddenApp, app.env=override
 *   2) Подключите оба через @PropertySource в одну конфигурацию.
 *      Порядок: сначала default.properties, затем override.properties.
 *   3) В компоненте PropertyPriorityDemo прочитайте через @Value:
 *        - app.name   — ожидается "OverriddenApp" (победил override.properties)
 *        - app.env    — ожидается "override"
 *        - app.debug  — ожидается "false" (есть только в default.properties)
 *   4) Объясните в комментарии: почему последний @PropertySource побеждает?
 *
 * ВАЖНО — ПРО YAML:
 *   @PropertySource НЕ поддерживает YAML «из коробки» в чистом Spring.
 *   Нужно написать PropertySourceFactory или использовать Spring Boot (модуль 65).
 *   В этом задании используем только .properties-файлы.
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ:
 *   app.name  = OverriddenApp   (из override.properties — последний → выше приоритет)
 *   app.env   = override
 *   app.debug = false           (только в default.properties — берётся оттуда)
 *
 * ПОДСКАЗКА:
 *   @Configuration
 *   @PropertySource("classpath:default.properties")
 *   @PropertySource("classpath:override.properties")
 *   public class MultiSourceConfig { ... }
 *
 * ПРИМЕР default.properties:
 *   # src/main/resources/default.properties
 *   app.name=DefaultApp
 *   app.env=default
 *   app.debug=false
 *
 * ПРИМЕР override.properties:
 *   # src/main/resources/override.properties
 *   app.name=OverriddenApp
 *   app.env=override
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

public class Task05 {

    public static void main(String[] args) {
        // TODO: создайте контекст с MultiSourceConfig.class
        //       получите бин PropertyPriorityDemo и вызовите printProperties()
    }
}
