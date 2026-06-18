package m30_design_patterns.practice.task07;

public class DrinkFactory {
    public static Drink createDrink(String type) {
        switch (type.toLowerCase()) {
            case "espresso":
                return new Espresso();
            case "latte":
                return new Latte();
            case "americano":
                return new Americano();
            case "cappuccino":
                return new Cappuccino();
            default:
                throw new IllegalArgumentException("Неизвестный тип напитка: " + type);
        }
    }
}
