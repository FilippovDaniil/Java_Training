// Abstract Factory: семья согласованных ингредиентов.
interface IngredientFactory {
    Dough createDough();
    Sauce createSauce();
}
