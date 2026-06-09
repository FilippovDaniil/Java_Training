class Pizza {
    // TODO: неизменяемые поля Dough dough, Sauce sauce, boolean extraCheese

    private Pizza(Builder b) {
        // TODO: скопировать поля из билдера
    }

    // TODO: describe()/toString():
    //       "Pizza{dough=.., sauce=.., extraCheese=..}" (через dough.describe()/sauce.describe())

    static class Builder {
        // TODO: поля Dough dough, Sauce sauce, boolean extraCheese

        // берёт согласованную семью ингредиентов из Abstract Factory
        public Builder ingredients(IngredientFactory factory) {
            // TODO: dough = factory.createDough(); sauce = factory.createSauce(); return this
            return this;
        }

        public Builder extraCheese(boolean extraCheese) {
            // TODO
            return this;
        }

        public Pizza build() {
            // TODO: вернуть new Pizza(this)
            return new Pizza(this);
        }
    }
}
