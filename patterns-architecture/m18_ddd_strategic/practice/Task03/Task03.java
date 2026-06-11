package m18_ddd_strategic.practice.task03;

/**
 * Задача 03 — Тема 18: Entity (равенство по идентичности)
 *
 * ЗАДАНИЕ:
 *   Реализуйте Customer (файл Customer.java) как сущность с идентичностью:
 *     - final поле id (String) — идентичность; изменяемое поле name;
 *     - конструктор Customer(String id, String name);
 *     - метод rename(String newName);
 *     - equals()/hashCode() ТОЛЬКО по id (имя на равенство не влияет);
 *     - геттеры.
 *   В main: создайте Customer, переименуйте его — это та же личность; создайте
 *   ВТОРОЙ Customer с тем же id, но другим именем — он equals первому (по id).
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   После rename: C-1 / Анна
 *   Тот же id, другое имя, equals: true
 *
 * ТРЕБОВАНИЯ:
 *   - equals/hashCode только по id;
 *   - смена имени не меняет идентичность (тот же объект — тот же клиент);
 *   - два объекта с одним id считаются равными независимо от имени.
 *
 * ПОДСКАЗКА:
 *   Entity: «важно КТО, а не текущее имя». Для Set/Map ключом служит id.
 */

public class Task03 {
    public static void main(String[] args) {
        // TODO: создайте Customer, rename, сравните с другим Customer (тот же id, иное имя)
    }
}
