/**
 * Задача 04 — Модуль 62: SpEL и значения по умолчанию в @Value
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: org.springframework:spring-context:6.1.x (см. theory.md)
 *
 * ЗАДАНИЕ:
 *   Используйте расширенные возможности @Value:
 *   1) Значение по умолчанию: @Value("${app.timeout:30}") — если ключ отсутствует, берётся 30.
 *   2) SpEL-арифметика: @Value("#{2 * 1024}") → 2048 (размер буфера в байтах).
 *   3) SpEL-системное свойство: @Value("#{systemProperties['user.name']}") — имя ОС-пользователя.
 *   4) SpEL с дефолтом (Elvis-оператор): @Value("#{environment['app.name'] ?: 'Unnamed'}").
 *   5) Смешанный: @Value("#{${app.timeout:30} * 1000}") — таймаут в миллисекундах.
 *
 *   Создайте компонент SpelDemo с этими пятью полями.
 *   В main() выведите все поля и убедитесь в ожидаемых значениях.
 *
 * РАЗНИЦА МЕЖДУ ${} и #{}:
 *   ${key}          — обращение к источникам свойств (PropertySource)
 *   ${key:default}  — то же, с дефолтом при отсутствии ключа
 *   #{expression}   — вычисление SpEL-выражения
 *   #{${key} * 2}   — сначала подставляется значение ${key}, затем умножается SpEL-ом
 *
 * ОЖИДАЕМЫЙ РЕЗУЛЬТАТ (без app.properties, без app.timeout):
 *   timeout         = 30           (дефолт из @Value)
 *   bufferSize      = 2048         (SpEL: 2 * 1024)
 *   osUser          = <ваш логин>  (из системных свойств)
 *   appNameOrDefault= Unnamed      (ключ не найден → Elvis)
 *   timeoutMs       = 30000        (дефолт 30 * 1000)
 *
 * ПОДСКАЗКА:
 *   @Value("${app.timeout:30}")
 *   private int timeout;
 *
 *   @Value("#{2 * 1024}")
 *   private int bufferSize;
 *
 * ПРИМЕР (если добавить app.properties):
 *   app.timeout=60   →  timeout=60, timeoutMs=60000
 *   app.name=SrvA    →  appNameOrDefault=SrvA
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

public class Task04 {

    public static void main(String[] args) {
        // TODO: создайте контекст с SpelConfig.class
        //       получите бин SpelDemo и выведите все поля
    }
}
