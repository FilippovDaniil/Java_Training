package m02_solid_srp_ocp.practice.task03;

/**
 * Задача 03 — Тема 02: OCP — убрать switch по типу
 *
 * ЗАДАНИЕ:
 *   Расчёт стоимости доставки раньше был построен на switch (type): добавить
 *   новый способ означало править рабочий код. Перепишите по OCP:
 *     - интерфейс ShippingMethod (файл ShippingMethod.java):
 *         String name();
 *         long costCents(int weightGrams);
 *     - реализации StandardShipping (5 коп./100 г) и ExpressShipping
 *       (12 коп./100 г);
 *     - класс DroneShipping (фикс. 500 коп.) — ДОБАВЬТЕ его, НЕ меняя
 *       Standard/Express (это и есть «открыт для расширения»).
 *   В main посчитайте доставку одного и того же веса всеми тремя способами.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   standard (300 г): 15
 *   express (300 г): 36
 *   drone (300 г): 500
 *
 * ТРЕБОВАНИЯ:
 *   - никакого switch/if по «типу доставки»;
 *   - добавление DroneShipping не требует правок в существующих классах;
 *   - вызывающий код работает с типом ShippingMethod.
 *
 * ПОДСКАЗКА:
 *   Это ручная реализация паттерна Strategy (тема 10). Вес в граммах →
 *   за каждые начатые 100 г: ((weightGrams + 99) / 100) * тариф.
 */

import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        // TODO: соберите List<ShippingMethod> из трёх реализаций,
        //       посчитайте costCents для одного веса каждым способом
    }
}
