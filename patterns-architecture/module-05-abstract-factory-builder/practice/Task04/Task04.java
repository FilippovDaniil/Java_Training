/**
 * Задача 04 — Тема 05: Abstract Factory (семейство UI-виджетов)
 *
 * ЗАДАНИЕ:
 *   Реализуйте абстрактную фабрику UI двух тем (светлая/тёмная):
 *     - продукты: Button (файл Button.java) и Checkbox (файл Checkbox.java),
 *       у каждого String render();
 *     - реализации: LightButton/LightCheckbox ("light ...") и
 *       DarkButton/DarkCheckbox ("dark ...");
 *     - абстрактная фабрика UiFactory (файл UiFactory.java):
 *         Button createButton(); Checkbox createCheckbox();
 *     - конкретные LightFactory и DarkFactory создают согласованную семью.
 *   В main напишите метод/код, который принимает UiFactory и рисует и кнопку,
 *   и чекбокс — прогоните его для светлой и тёмной фабрики.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   light button
 *   light checkbox
 *   dark button
 *   dark checkbox
 *
 * ТРЕБОВАНИЯ:
 *   - клиентский код работает с UiFactory/Button/Checkbox, не с конкретными классами;
 *   - нельзя случайно смешать light-кнопку с dark-чекбоксом (всё из одной фабрики);
 *   - добавление новой темы = новая фабрика + её продукты, без правок клиента.
 *
 * ПОДСКАЗКА:
 *   Abstract Factory гарантирует СОГЛАСОВАННОСТЬ семьи: один объект-фабрика
 *   создаёт все продукты одного стиля.
 */

public class Task04 {
    public static void main(String[] args) {
        // TODO: прогоните рендер кнопки и чекбокса для LightFactory и DarkFactory
    }
}
