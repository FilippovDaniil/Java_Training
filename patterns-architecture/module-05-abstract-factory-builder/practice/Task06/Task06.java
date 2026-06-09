/**
 * Задача 06 — Тема 05: Builder + Abstract Factory вместе
 *
 * ЗАДАНИЕ:
 *   Соберите пиццу, где СЕМЬЮ ингредиентов даёт Abstract Factory, а сам объект
 *   собирает Builder:
 *     - продукты: Dough (файл Dough.java) и Sauce (файл Sauce.java), у каждого
 *       String describe();
 *     - реализации: ThinDough/ThickDough, MarinaraSauce/KetchupSauce;
 *     - IngredientFactory (файл IngredientFactory.java): Dough createDough();
 *       Sauce createSauce(); семьи ItalianFactory (Thin + Marinara) и
 *       AmericanFactory (Thick + Ketchup);
 *     - Pizza (файл Pizza.java) с Builder: метод ingredients(IngredientFactory f)
 *       берёт тесто и соус ИЗ фабрики, метод extraCheese(boolean) — опция,
 *       build() собирает Pizza.
 *   В main соберите итальянскую и американскую пиццу, выведите состав.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Pizza{dough=thin, sauce=marinara, extraCheese=false}
 *   Pizza{dough=thick, sauce=ketchup, extraCheese=true}
 *
 * ТРЕБОВАНИЯ:
 *   - ингредиенты согласованы (берутся из одной IngredientFactory);
 *   - порядок/набор шагов сборки задаёт Builder (extraCheese — опционально);
 *   - Pizza неизменяема после build().
 *
 * ПОДСКАЗКА:
 *   Abstract Factory отвечает на «ИЗ ЧЕГО собирать» (семья частей), Builder —
 *   на «КАК собирать» (шаги и опции). Это классическая связка из «пиццерии».
 */

public class Task06 {
    public static void main(String[] args) {
        // TODO: соберите Pizza через Builder с ItalianFactory и с AmericanFactory
    }
}
