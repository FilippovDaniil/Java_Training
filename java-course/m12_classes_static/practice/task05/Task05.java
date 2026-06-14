package m12_classes_static.practice.task05;

/**
 * Задача 05 — Модуль 12: Блоки инициализации
 *
 * ЗАДАНИЕ:
 *   В классе AppConfig:
 *     - статический блок инициализации, который задаёт static-поле
 *       version = "1.0" и печатает "Конфигурация загружена";
 *     - блок инициализации экземпляра, печатающий "Создан объект config".
 *   В main создайте ДВА объекта AppConfig и понаблюдайте за порядком
 *   вывода: статический блок выполнится ОДИН раз, блок экземпляра —
 *   при каждом создании.
 *
 * ОЖИДАЕМЫЙ ВЫВОД:
 *   Конфигурация загружена
 *   Создан объект config
 *   Создан объект config
 *   Версия: 1.0
 *
 * ПОДСКАЗКА:
 *   static { ... } — статический блок; { ... } — блок экземпляра.
 */

public class Task05 {
    public static void main(String[] args) {
        // Создайте 2 объекта AppConfig, затем выведите AppConfig.version
        AppConfig appConfig = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        AppConfig appConfig3 = new AppConfig();
        AppConfig appConfig24 = new AppConfig();
    }
}
