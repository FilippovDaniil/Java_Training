/**
 * Задача 05 — Модуль 26: Собственная аннотация
 *
 * ЗАДАНИЕ:
 *   Объявите собственную аннотацию @Info с параметрами:
 *     - String author();
 *     - String version() default "1.0";
 *   Сделайте её доступной во время выполнения (@Retention(RUNTIME))
 *   и применимой к классам (@Target(TYPE)).
 *   Пометьте ею класс Module и выведите значения аннотации через
 *   рефлексию.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Автор: Иванов, версия: 2.5
 *
 * ПОДСКАЗКА:
 *   @Retention(RetentionPolicy.RUNTIME) @Target(ElementType.TYPE)
 *   @interface Info { String author(); String version() default "1.0"; }
 *   Чтение: Module.class.getAnnotation(Info.class).author();
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Task05 {
    public static void main(String[] args) {
        // Прочитайте аннотацию @Info у класса Module через рефлексию
    }
}
