package m05_abstract_factory_builder.practice.task06;

// Abstract Factory: семья согласованных ингредиентов.
interface IngredientFactory {
    Dough createDough();
    Sauce createSauce();
}
