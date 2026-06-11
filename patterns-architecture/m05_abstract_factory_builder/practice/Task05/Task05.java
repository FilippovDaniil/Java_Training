package m05_abstract_factory_builder.practice.task05;

/**
 * Задача 05 — Тема 05: Abstract Factory (семейство для разных СУБД)
 *
 * ЗАДАНИЕ:
 *   Создайте согласованные семьи объектов доступа к БД для MySQL и Postgres:
 *     - продукты: Connection (файл Connection.java) с String connect() и
 *       Query (файл Query.java) с String run(String sql);
 *     - реализации: MySqlConnection/MySqlQuery, PostgresConnection/PostgresQuery
 *       (печатают свой диалект, например "mysql: connected", "postgres: SELECT ...");
 *     - DbFactory (файл DbFactory.java): Connection createConnection(); Query createQuery();
 *     - MySqlFactory и PostgresFactory.
 *   В main выберите фабрику по строке-конфигу ("mysql"/"postgres") и через
 *   неё создайте connection+query — они гарантированно одного диалекта.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   mysql: connected
 *   mysql: SELECT * FROM orders
 *   postgres: connected
 *   postgres: SELECT * FROM orders
 *
 * ТРЕБОВАНИЯ:
 *   - connection и query всегда берутся из ОДНОЙ фабрики (нельзя смешать СУБД);
 *   - выбор фабрики (по конфигу) — единственное место, знающее о конкретике;
 *   - клиентский код далее работает только с DbFactory/Connection/Query.
 *
 * ПОДСКАЗКА:
 *   Сравните с Task04: там тема UI, здесь — СУБД, но паттерн тот же. Выбор
 *   конкретной фабрики обычно делается один раз при старте приложения.
 */

public class Task05 {
    public static void main(String[] args) {
        // TODO: по строке-конфигу выберите DbFactory, создайте Connection и Query,
        //       вызовите connect() и run(sql) для mysql и postgres
    }
}
